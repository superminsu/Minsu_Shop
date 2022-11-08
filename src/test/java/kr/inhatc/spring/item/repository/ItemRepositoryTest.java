package kr.inhatc.spring.item.repository;

import static kr.inhatc.spring.item.entity.QItem.item;
import static org.assertj.core.api.Assertions.from;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.inhatc.spring.item.constant.ItemSellStatus;
import kr.inhatc.spring.item.entity.Item;
import kr.inhatc.spring.item.entity.QItem;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
@Transactional  //테스트할 때만 db 만들고 테스트 끝나면 롤백시킴
class ItemRepositoryTest {

	@Autowired
	EntityManager em;

	@Autowired
	ItemRepository itemRepository;

	@Test
	@DisplayName("상품 저장 테스트")
	public void createItemTest() {
		Item item = new Item();
		item.setItemName("테스트 상품");
		item.setPrice(10000);
		item.setItemDetail("테스트 상품 상세 설명");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		Item savedItem = itemRepository.save(item);
		System.out.println(savedItem.toString());
	}

	public void createItemList() {
		for (int i = 1; i <= 10; i++) {
			Item item = new Item();
			item.setItemName("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());

			Item savedItem = itemRepository.save(item);
		}
	}

	@Test
	@DisplayName("상품명 조회 테스트")
	public void findByItemNmTest() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemName("테스트 상품1");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("상품명, 상품상세설명 or 테스트")
	public void findByItemNmOrItemDetailTest() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemNameOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("JPQL 테스트")
	public void findByItemDetailTest() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemDetail("테스트");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("Native 테스트")
	public void findByItemDetailNativeTest() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemDetailNative("테스트");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("Querydsl 조회")
	public void queryDslTest() {
		this.createItemList();

		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

		List<Item> fetch = jpaQueryFactory.selectFrom(item)
				.where(item.itemSellStatus.eq(ItemSellStatus.SELL))
				.where(item.itemDetail.like("%" + "1" + "%")).orderBy(item.price.asc()).fetch();

		for (Item item : fetch) {
			log.info(item);
		}
	}

	public void createItemList2() {
		for (int i = 1; i <= 5; i++) {
			Item item = new Item();
			item.setItemName("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			itemRepository.save(item);
		}

		for (int i = 6; i <= 10; i++) {
			Item item = new Item();
			item.setItemName("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
			item.setStockNumber(0);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			itemRepository.save(item);
		}
	}

	@Test
	@DisplayName("Querydsl 조회2")
	public void queryDslTest2() {
		createItemList2();
		
		String itemDetail = "테스트";
		int price = 10003;
		String itemSellStat = "SELL";
		
		QItem item = QItem.item;
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
		booleanBuilder.and(item.price.gt(price));
		
		if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)) {
			booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
		}
		
		Pageable pageable = PageRequest.of(0, 5);
		
		Page<Item> result = itemRepository.findAll(booleanBuilder, pageable);
		
		log.info("총 갯수 : " + result.getTotalElements());
		
		List<Item> content = result.getContent();
		
		for (Item item2 : content) {
			log.info(item2);
		}
	}
	
	@Test
	void test() {
		Item item = new Item();
		item.setItemName("내 물픔");
		System.out.println(item);
	}

}
