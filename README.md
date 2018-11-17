### wechatStudy ###

###### wechat公众号接口模块： ######
1. 暂时引用fastweixin
2. 

###### redis使用相关： ######
1. 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
2. 

###### mysql使用相关： ######
1. 实体类生成使用generatorConfigBase.xml或generatorConfigShiro.xml，加上idea插件
2. 双数据源根据mapper所在包自动切换

###### error log： ######
1.出现2个一样的模板在容器中怎么确认哪一个为有限级或者说那一个是主要的：
  添加注解@Primary 在方法上
  
  ~~~
  @RequiresPermissions({"DEL"})
  shiro具体权限使用该注解
  ~~~