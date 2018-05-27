Refer to the documentation for the MyBatis Generator application:
http://www.mybatis.org/generator/
http://www.mybatis.org/generator/running/runningFromCmdLine.html

To run the MyBatis Generator application and regenerate the POJOs, MyBatis mappers, and xml mapper files, execute the following:

windows
java -cp mybatis-generator-core-1.3.5.jar;mysql-connector-java-5.1.43.jar org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml

linux
java -cp mybatis-generator-core-1.3.5.jar：mysql-connector-java-5.1.43.jar org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml