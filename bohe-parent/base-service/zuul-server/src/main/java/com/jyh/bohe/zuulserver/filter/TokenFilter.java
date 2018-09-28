package com.jyh.bohe.zuulserver.filter;

import com.alibaba.fastjson.JSON;
import com.jyh.bohe.boheapi.bean.AjaxResponseBody;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


public class TokenFilter extends ZuulFilter {
    private final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public String filterType() {
        return "pre"; // 可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 2; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());
        //放行鉴权服务 其他的有token放行
        String destinationService = request.getRequestURL().toString();
        boolean isFreeService = FilterUtil.filterFree(destinationService);
        boolean isAuthService = FilterUtil.filterAuth(destinationService);
        if (isFreeService) {
            filterCondition(ctx, true);
        } else {
            String token = request.getHeader("Authorization");
            if (StringUtils.isNotBlank(token)) {
                filterCondition(ctx, true);
            } else {
                filterCondition(ctx, false);
            }
        }
        return null;
    }

    public void filterCondition(RequestContext ctx, boolean result) {
        AjaxResponseBody responseBody = new AjaxResponseBody();
        if (result == false) {
            ctx.setSendZuulResponse(false); //不对其进行路由
            responseBody.setStatus("400");
            responseBody.setMsg("token is empty!");
            ctx.set("isSuccess", false);
            ctx.setResponseBody(JSON.toJSONString(responseBody));
        } else {
            ctx.setSendZuulResponse(true); //对请求进行路由
            responseBody.setStatus("200");
            ctx.set("isSuccess", true);
        }
    }
}