package com.cp.app.core.model.bean.attr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName DateAttributeConverter
 * @Description TODO 时间字段处理
 * @createdate 2019/7/27 星期六 08:24
 */
public class DateAttributeConverter implements AttributeConverter<String,Date> {
    private static  final Logger LOGGER = LoggerFactory.getLogger(DateAttributeConverter.class);
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public Date convertToDatabaseColumn(String attribute) {
        //将实体类中的将String类型的数据字段转为Date类型存储到数据库中，（插入和更新操作时执行）；
        if(StringUtils.isEmpty(attribute)){
            return null;
        }
        try {
            return format.parse(attribute);
        } catch (ParseException e) {
            LOGGER.warn("时间字符串格式不正确!");
            return null;
        }
    }
    @Override
    public String convertToEntityAttribute(Date dbData) {
        //将数据库中Date类型字段转为实体类String类型属性，查询时执行
        if (null==dbData) {
            LOGGER.warn("数据库数据为空!");
            return "";
        }
        try{
            return format.format(dbData);
        }catch (Exception e){
            LOGGER.warn("时间类型转换异常!");
            return "";
        }
    }
}
