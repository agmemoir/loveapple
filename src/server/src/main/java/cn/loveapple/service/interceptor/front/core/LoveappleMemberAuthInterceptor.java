/**
 * 
 */
package cn.loveapple.service.interceptor.front.core;

import static cn.loveapple.service.util.web.FrontUtil.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.loveapple.service.controller.SessionLabel;

/**
 * Google会員向けコンテンツ(URL:/core/*)のインターセプタ、Googleの認証処理を行う。
 * 
 * @author hao_shunri
 *
 */
public class LoveappleMemberAuthInterceptor extends HandlerInterceptorAdapter implements SessionLabel {

	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(LoveappleMemberAuthInterceptor.class);
	
	/**
	 * VIEWが返された後、実行する。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * コントローラが実行された後、実行する。
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * コントローラが実行される直前、実行する。<br>
	 * 会員のログイン状態を確認し、まだログインしていない状態の場合、ログイン画面へリダイレクトする。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(!hasAttributeInSession(request, LOVEAPPLE_MEMBER)){
			HttpSession session = request.getSession(true);
			String requestUrl = createFullRequestUrl(request);
			session.setAttribute(REFERER_INNER_URL, requestUrl);
			log.info("Member info is empty ref. REFERER_INNER_URL:" + requestUrl);
			response.sendRedirect("/member");
			return false;
		}
		return true;
	}
}
