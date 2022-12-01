package com.visionary.crofting;

import com.visionary.crofting.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.stream.Stream;
@SpringBootTest
class VisionaryCroftingApplicationTests {
	@Autowired
	GenericWebApplicationContext context;
	@Test
	void contextLoads() {
	}
	@Test
	public void testModelMapperForOrderItem(){
		/*System.out.println("all beans list");
		Stream.of(context.getBeanDefinitionNames()).filter(n->n.contains("TestBean")).forEach(System.out::println);
		System.out.println("bean class name is "+		BeanFactoryAnnotationUtils.qualifiedBeanOfType(context.getBeanFactory(),TestBean.class,"testBean12").getName());
		System.out.println("end beans list");
		System.out.println("applicationContext "+context.getClass().getCanonicalName());
*/
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
