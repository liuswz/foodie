package com.lanke.foodie.config;

import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;
import java.util.regex.Pattern;
@Slf4j
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {
    private Pattern pattern;
    /**
     * description:判断是否匹配这个字符串
     * @param: [url]用户请求的连接
     * @return: true:不需要拦截
     *          false:必须要登录
     */
    @Override
    public boolean matches(String url) {
        //使用正则表达式来匹配需要忽略的连接

        if(url.contains("/common/logout")||url.contains("/consumer/shopdetail/register")||url.contains("/common/redirectlogin")){
            return true;
        }


        return this.pattern.matcher(url).find();
    }

    /**
     * description:正则表达式的规则，该规则在配置AuthenticationFilter的ignorePattern中设置
     * @param: [pattern]
     * @return: void
     */
    @Override
    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }
}