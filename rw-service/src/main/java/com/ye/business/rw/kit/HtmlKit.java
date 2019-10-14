package com.ye.business.rw.kit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * *类说明：页面渲染
 *
 * </p>
 * *详细描述：
 * 
 * @author sjiying
 * @CeateDate 2019年5月7日
 */
public class HtmlKit {

	private static Logger log = LoggerFactory.getLogger(HtmlKit.class);

	/**
	 * 
	 * *方法说明：
	 *
	 * @param uri
	 * @return
	 * @throws FailingHttpStatusCodeException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @author sjiying
	 * @CreateDate 2019年5月8日
	 */
	public static String doGet(String uri) {

		log.info("HtmlUnit GET 请求开始：{}", uri);

		String rs = "";
		try (WebClient wc = new WebClient(BrowserVersion.CHROME);) {

			wc.setJavaScriptTimeout(5000);
			wc.getOptions().setUseInsecureSSL(true); // 接受任何主机连接 无论是否有有效证书
			wc.getOptions().setJavaScriptEnabled(true); // 设置支持javascript脚本
			wc.setAjaxController(new NicelyResynchronizingAjaxController()); // 设置支持 ajax
			wc.getOptions().setCssEnabled(true); // 禁用css支持-false
			wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时不抛出异常
			wc.getOptions().setTimeout(100000); // 设置连接超时时间
			wc.getOptions().setDoNotTrackEnabled(false);

			// 设置宽高：模拟手机 Iphone X
			wc.getOptions().setScreenHeight(375);
			wc.getOptions().setScreenWidth(812);

			wc.waitForBackgroundJavaScript(100000);

			HtmlPage page = wc.getPage(uri);

			rs = page.asXml();

		} catch (FailingHttpStatusCodeException e) {
			log.error("HtmlUnit GET 请求状态码错误：", e);
		} catch (MalformedURLException e) {
			log.error("HtmlUnit GET 请求URL错误：", e);
		} catch (IOException e) {
			log.error("HtmlUnit GET IO异常：", e);
		}

		return rs;
	}

	public static Map<String, String> doGetMap(String uri) {

		log.info("HtmlUnit GET 请求开始：{}", uri);

		Map<String, String> rs = new HashMap<>();

		try (WebClient wc = new WebClient(BrowserVersion.CHROME);) {

			wc.setJavaScriptTimeout(5000);
			wc.getOptions().setUseInsecureSSL(true); // 接受任何主机连接 无论是否有有效证书
			wc.getOptions().setJavaScriptEnabled(true); // 设置支持javascript脚本
			wc.setAjaxController(new NicelyResynchronizingAjaxController()); // 设置支持 ajax
			wc.getOptions().setCssEnabled(true); // 禁用css支持-false
			wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时不抛出异常
			wc.getOptions().setTimeout(100000); // 设置连接超时时间
			wc.getOptions().setDoNotTrackEnabled(false);

			// 设置宽高：模拟手机 Iphone X
			wc.getOptions().setScreenHeight(375);
			wc.getOptions().setScreenWidth(812);

			HtmlPage page = wc.getPage(uri);
			wc.waitForBackgroundJavaScript(10000);// 异步JS执行需要耗时,所以这里线程要阻塞10秒,等待异步JS执行结束

			DomNodeList<HtmlElement> node = page.getBody().getElementsByTagName("img");
			String img = node.stream().filter(ac -> !ac.getAttribute("src").startsWith("data:")).findFirst().get().getAttribute("src");
			rs.put("img", img);

			String title = page.getTitleText();
			if (StringUtils.isBlank(title)) {
				String text = page.asText();
				if (StringUtils.isNotBlank(text)) {
					title = text.substring(0, text.indexOf("\r\n"));
					rs.put("title", title);
				}
			}
			rs.put("title", title);

			// 取 body内容
			rs.put("body", page.getBody().asXml());

		} catch (FailingHttpStatusCodeException e) {
			log.error("HtmlUnit GET 请求状态码错误：", e);
		} catch (MalformedURLException e) {
			log.error("HtmlUnit GET 请求URL错误：", e);
		} catch (IOException e) {
			log.error("HtmlUnit GET IO异常：", e);
		}

		return rs;
	}

}
