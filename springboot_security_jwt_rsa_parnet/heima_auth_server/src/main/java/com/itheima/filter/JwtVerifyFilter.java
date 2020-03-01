package com.itheima.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.config.RsaKeyProperties;
import com.itheima.domain.Payload;
import com.itheima.domain.SysUser;
import com.itheima.utils.JwtUtils;

public class JwtVerifyFilter extends BasicAuthenticationFilter {

	private RsaKeyProperties prop;

	public JwtVerifyFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
		super(authenticationManager);
		this.prop = prop;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			// 如果携带错误的token,则给用户提示请登录
			response.setContentType("application/json;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			PrintWriter out = response.getWriter();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("code", HttpServletResponse.SC_FORBIDDEN);
			resultMap.put("msg", "请登录");
			out.write(new ObjectMapper().writeValueAsString(resultMap));
			out.flush();
			out.close();
		} else {
			// 如果携带了正确格式的token
			String token = header.replace("Bearer ", "");
			try {
				// 验证token是否正确
				Payload<SysUser> payload = JwtUtils.getInfoFromToken(token, prop.getPublicKey(), SysUser.class);
				SysUser sysUser = payload.getUserInfo();
				if (sysUser != null) {
					UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), null, sysUser.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authResult);
					chain.doFilter(request, response);
				}
			} catch (Exception e) {
				try {
					response.setContentType("application/json;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					PrintWriter out = response.getWriter();
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("code", HttpServletResponse.SC_UNAUTHORIZED);
					resultMap.put("msg", "认证失败");
					out.write(new ObjectMapper().writeValueAsString(resultMap));
					out.flush();
					out.close();
				} catch (Exception outEx) {
					outEx.printStackTrace();
				}
			}
		}
	}

}
