package com.xd;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class initCode {
    public static void main(String[] args) {
//        代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
//        策略配置
//        全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java")
// ================！！！！覆盖原有文件，不需要时请注释！！！！=======================
//                .setFileOverride(true)
                .setAuthor("Malloc")
                .setOpen(false)
                .setSwagger2(true)
                .setDateType(DateType.ONLY_DATE)
                .setIdType(IdType.AUTO)
                .setServiceName("%sService");
        autoGenerator.setGlobalConfig(gc);
//        配置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUsername("root")
                .setPassword("root")
                .setUrl("jdbc:mysql://localhost:3306/myblog?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8")
                .setDriverName("com.mysql.cj.jdbc.Driver");
//                .setDbType(DbType.MYSQL); 默认mysql
        autoGenerator.setDataSource(dsc);
//      配置包
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.xd");
        autoGenerator.setPackageInfo(packageConfig);
//      配置策略
        List<TableFill> tableFills = new ArrayList<>();
        TableFill tableFill = new TableFill("create_time", FieldFill.INSERT);
        TableFill tableFill2 = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        tableFills.add(tableFill);
        tableFills.add(tableFill2);
        StrategyConfig strategyConfig = new StrategyConfig()
            .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setChainModel(true)
                .setEntityLombokModel(true)
                .setLogicDeleteFieldName("deleted")
                .setTableFillList(tableFills)
                .setInclude("t_blog","t_comment","t_friend","t_message","t_picture","t_type","t_user");//,
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.execute();
    }
}
