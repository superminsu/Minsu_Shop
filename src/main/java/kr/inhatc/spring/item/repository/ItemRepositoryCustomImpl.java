package kr.inhatc.spring.item.repository;

import static kr.inhatc.spring.item.entity.QItem.item;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.inhatc.spring.item.constant.ItemSellStatus;
import kr.inhatc.spring.item.dto.ItemSearchDto;
import kr.inhatc.spring.item.entity.Item;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    private JPAQueryFactory queryFactory;
    
    public ItemRepositoryCustomImpl(EntityManager em) { //ItemRepositoryCustomImpl 호출시 queryFactory 사용
        queryFactory = new JPAQueryFactory(em);
    }
    
    private BooleanExpression regDtsAfter(String searchDateType) {
        // 기간처리
        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        } else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        } else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        } else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        } else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }

        return item.regTime.after(dateTime);
    }
    
    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
        // serachSellStatus 검색 여부
        return searchSellStatus == null ? null : item.itemSellStatus.eq(searchSellStatus);
    }
    
    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        // searchBy 검색 여부
        if(StringUtils.equals("itemName", searchBy)){
            return item.itemName.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("createdBy", searchBy)){
            return item.createdBy.like("%" + searchQuery + "%");
        }

        return null;
    }
    
    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        //페이징 조건 검색
        List<Item> list = queryFactory
            .selectFrom(item)
            .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                    searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                    searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
            .orderBy(item.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
        
        long total = queryFactory.select(Wildcard.count).from(item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .fetchOne();
        
        return new PageImpl<>(list, pageable, total);
    }
}
