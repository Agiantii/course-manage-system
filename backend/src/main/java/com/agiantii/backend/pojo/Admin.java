package com.agiantii.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;

@TableName("t_admin")
@Data
public class Admin {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
}
