package com.sf.jkt.k.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;

import java.util.*;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class MybatisPlusGenerator {

    /**
     * 常用的配置项目单独抽出来 方便理解
     */
    @Data
    static class CF {

        String AUTHOR = "songfei";
        //数据库配置
        String dbUrl = "";
        String dbUser = "";
        String dbPassword = "";
        //项目模块配置
        String parentPackage = "";
        String moduleName = "";
        //是否生成批量
        boolean genBatch = false;
        //是否生成自定义单笔
        boolean genSingle = false;
        //自动生成代码的表
        String[] tableNames = new String[]{};
        //表前缀
        String[] tablePrefixs = new String[]{"tbl", "Tbl"};
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        genMappers();
    }

    /**
     * 常用配置
     */
    public static CF genCF() {
        CF cf = new CF();
        cf.setAUTHOR("songfei");
        cf.setDbUrl("jdbc:mysql://127.0.0.1:3306/test");
        cf.setDbUser("root");
        cf.setDbPassword("root");
        cf.setParentPackage("com.sf.jkt.k");
        cf.setModuleName("auto");
        cf.setGenSingle(false);
        cf.setGenBatch(false);
        cf.setTableNames(new String[]{"user"});
        return cf;
    }


    public static String resolveProjectPath() {
        String cmdPath = MybatisPlusGenerator.class.getResource("/").getPath();
        int targetIndex = cmdPath.indexOf("target");
        String projectPath = cmdPath.substring(0, targetIndex);
        return projectPath;
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void genMappers() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        CF cf = genCF();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = resolveProjectPath();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(cf.getAUTHOR());
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setKotlin(true);
        gc.setActiveRecord(true);
        gc.setSwagger2(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(cf.getDbUrl());
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(cf.getDbUser());
        dsc.setPassword(cf.getDbPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        //pc.setXml("mymapper");
        pc.setParent(cf.getParentPackage());
        pc.setModuleName(cf.getModuleName());
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<String, Object>();
                if (cf.isGenSingle()) {
                    map.put("genInsert", true);
                    map.put("genDelete", true);
                    map.put("genUpdate", true);
                    map.put("genSelect", true);
                }
                if (cf.isGenBatch()) {
                    map.put("genInsertBatch", true);
                    map.put("genDeleteBatch", true);
                    map.put("genUpdateBatch", true);
                    map.put("genSelectBatch", true);
                }
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String output = projectPath + "/src/main/resources/" + pc.getParent().replaceAll("\\.", "/")
                        + "/" + "mapper" + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                return output;
            }
        });

        focList.add(new FileOutConfig("/templates/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String output = projectPath + "/src/main/java/" + pc.getParent().replaceAll("\\.", "/")
                        + "/" + "mapper" + "/" + tableInfo.getEntityName() + "Mapper" + ".kt";
                return output;
            }
        });

        focList.add(new FileOutConfig("/templates/entity.kt.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String output = projectPath + "/src/main/java/" + pc.getParent().replaceAll("\\.", "/")
                        + "/" + "entity" + "/" + tableInfo.getEntityName() + "" + ".kt";
                return output;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntit((PackageConfig)pc).controlleryClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        //       strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
//        strategy.setInclude(scanner("表名"));
        strategy.setInclude(cf.getTableNames());
//    strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
//    strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setTablePrefix(cf.getTablePrefixs());

        strategy.setEntityBuilderModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityColumnConstant(true);
        strategy.entityTableFieldAnnotationEnable(true);
        //strategy.setTableFillList()
//    TableFill tableFill = new TableFill("user_status", FieldFill.INSERT_UPDATE);
        List<TableFill> list = new ArrayList<>();
//    list.add(tableFill);
        strategy.setTableFillList(list);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
