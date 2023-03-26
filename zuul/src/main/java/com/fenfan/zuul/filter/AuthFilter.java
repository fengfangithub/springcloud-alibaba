package com.fenfan.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengfan
 * @description 身份验证
 * @date 2022/5/26 16:18
 */
public class AuthFilter extends ZuulFilter {

    /**
     * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。
     * pre：在请求被路由之前执行
     * routing：在路由请求时被调用
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序，请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断过滤器是否需要被执行，返回了true,因此该过滤器对所有请求都会生效。实际运用中我们可以利用该函数来指定过滤器的有效范围
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体执行逻辑，这里判断请求是否携带token，如果不携带则令zuul过滤该请求，不对其进行路由
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //获取token
        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            //过滤该请求
            ctx.setSendZuulResponse(false);
            //设置未授权返回响应码
            ctx.setResponseStatusCode(401);
            return null;
        }
        return null;
    }
}
