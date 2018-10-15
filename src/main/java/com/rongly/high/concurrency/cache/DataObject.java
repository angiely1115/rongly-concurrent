package com.rongly.high.concurrency.cache;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/14 13:01
 * @Version: 1.0
 * modified by:
 */
@Data
public class DataObject implements Serializable{
    private String name;

    private ZonedDateTime zonedDateTime;

}
