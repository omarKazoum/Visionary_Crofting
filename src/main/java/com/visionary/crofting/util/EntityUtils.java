package com.visionary.crofting.util;

import com.visionary.crofting.entity.OrderItem;
import com.visionary.crofting.requests.OrderItemDTO;
import org.modelmapper.ModelMapper;

public class EntityUtils {

    public static OrderItemDTO orderItemDTOToOrderItem(OrderItem orderItem){
        ModelMapper modelMapper =new ModelMapper();
        OrderItemDTO oidto=modelMapper.map(orderItem,OrderItemDTO.class);
        return oidto;
    }
}
