package cn.trasen;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: project-parent
 * @description: qidonglei
 * @author: yan_zt
 * @create: 2020-06-22 11:33
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableSwagger2
@EnableSwaggerBootstrapUI
@MapperScan(basePackages = "cn.trasen.mcpc.transform.dao")
public class TransformApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransformApplication.class,args);
    }
}
