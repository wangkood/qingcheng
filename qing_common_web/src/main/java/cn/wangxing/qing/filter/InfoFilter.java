package cn.wangxing.qing.filter;

import javax.servlet.*;
import java.io.IOException;

public class InfoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        long start = System.nanoTime();

        filterChain.doFilter(request,response);

        long end = System.nanoTime();

        System.out.println("【统计】: \t\t用时="+ (end-start)/1000000 +"ms");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
