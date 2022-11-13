package com.zyxx.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyxx.common.enums.BaseEnums;
import com.zyxx.common.enums.StatusEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Data
public class MsRestResultData<T> implements Serializable {

    /**
     * 接口状态
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 接口数据长度
     */
    private Long count;

    /**
     * 接口数据
     */
    private List<T> data;

    /**
     * 无参构造函数
     */
    public MsRestResultData() {
        super();
    }

    /**
     * 返回数据给表格
     */
    public MsRestResultData(Long count, List<T> data) {
        super();
        this.count = count;
        this.data = data;
        this.code = 0;
    }

    /**
     * 返回数据给表格
     */
    public MsRestResultData(Integer count, List<T> data) {
        super();
        this.count = count.longValue();
        this.data = data;
        this.code = 0;
    }

    /**
     * 返回数据给表格
     * <p>
     * 配合mybatisplus分页查询使用
     */
    public MsRestResultData(IPage<T> iPage) {
        super();
        this.count = iPage.getTotal();
        this.data = iPage.getRecords();
        this.code = 0;
    }


}
