package com.visionary.crofting;

import com.visionary.crofting.entity.Order;
import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.entity.Product;
import com.visionary.crofting.request.OrderItemDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.spi.NameTokenizer;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.attribute.standard.Destination;

class VisionaryCroftingApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void testModelMapperForOrderItem(){
//		Order o=new Order();
//		o.setClient(null);
//		o.setId(2L);
//
//		Product p=new Product();
//		p.setId(1L);
//		OrderItem oi=new OrderItem();
//		oi.setId(99L);
//		oi.setOrder(o);
//		oi.setTotalPrice(100);
//		oi.setProduct(p);
//		oi.setQuantity(60);
//
//		ModelMapper modelMapper=new ModelMapper();
//		/*modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.CAMEL_CASE).setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
//		modelMapper.typeMap(OrderItem.class,OrderItemDTO.class)
//				.addMappings(mapper->{
//
//			mapper.map(src->
//				src.getId()
//			, (des,id)->des.setId((Long)id));
//		});*/
//		OrderItemDTO oidto=modelMapper.map(oi,OrderItemDTO.class);
//		System.out.println(oidto);
		Order order=new Order();

	}


}
