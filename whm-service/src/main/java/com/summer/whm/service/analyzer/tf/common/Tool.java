/*
 * IBM Confidential
 * 
 * Copyright IBM Corp. 2014,
 *
 * The source code for this program is not published or otherwise divested of its trade secrets,
 * irrespective of what has been deposited with the U.S. Copyright Office.
 */

package com.summer.whm.service.analyzer.tf.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.summer.whm.service.analyzer.tf.TKAnalyzerTool;

public class Tool {
	static class LinkedTerm{
		public String term;
		public LinkedTerm next;
	}
	
	static class IF_StopWord{
		private static IF_StopWord instance = null;
		private HashSet<String> removeWord = new HashSet<String>();
		private HashMap<String, String> replaceWord = new HashMap<String, String>();
		private HashMap<String, String> overrideWord = new HashMap<String, String>();
		private boolean inited = false;
		
		private IF_StopWord(){
			
		}
		public void init(String path){
			if(inited)
				return;
			File f = new File(path +  File.separator +  "if_stopword.dic");
			try {
				Scanner sc = new Scanner( new FileInputStream(f));
				while(sc.hasNextLine()) {
					String line = sc.nextLine();
					if( line.indexOf("=")!= -1){
						// replace an uncommon word to target words
						// be careful not to replace one word with multiple words,
						// it fails!!
						String[] re = line.split("=");
						replaceWord.put(re[0], re[1]);
					}if( line.indexOf("-") != -1) {
						String[] ov = line.split("-");
						overrideWord.put(ov[0], ov[1]);
					} else{
						removeWord.add(line);
					}
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			inited = true;
		}
		public static synchronized IF_StopWord getInstance(){
			if(instance == null) {
				instance = new IF_StopWord();
			}
			return instance;
		}
		
		public String override(String key) {
			return overrideWord.get(key);
		}
		public String has(String s){
			if( replaceWord.containsKey(s) ){
				return replaceWord.get(s);
			} else if( removeWord.contains(s) ){
				return null;
			}
			return s;
		}
	}
	
	/**
	 * To prevent a spam webpage add too many keywords,
	 * Each keyword will multiply with the docs weights.
	 * @param docs
	 * @param analyzer
	 * @param topK
	 * @return
	 */
	private static final int WEIGHT_PER_DOC = 3;
	public static void initIF(String path){
		IF_StopWord.getInstance().init(path);
	}
	public static String[] tf(String doc,  int topK){
		HashMap<String, Integer> bag = new HashMap<String, Integer>(); 
		for(String term : TKAnalyzerTool.getInstance().parse(doc)){
			Integer count = bag.get(term);
			if(count == null){
				count = 0;
			}
			bag.put(term, count + 1);
		}
		List<Map.Entry<String, Integer>  > li = new ArrayList<Map.Entry<String, Integer>>(bag.entrySet());
		Collections.sort(li, new Comparator<Map.Entry<String, Integer>>(){
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if(o1.getValue() < o2.getValue())
					return 1;
				else if(o1.getValue() > o2.getValue())
					return -1;
				return 0;
			}
			
		});
		int min = Math.min(li.size(), topK);
		String[] terms = new String[min];
		for(int i=0; i<min; i++){
		    terms[i] = li.get(i).getKey();
		    //System.out.println(li.get(i).getKey() + ":" + li.get(i).getValue());
		}
			
		return terms;
	}
	
	
	public static String[] tdf(List<String> docs, int topK) {
		HashMap<String, Integer> tf = new HashMap<String, Integer>();
		HashMap<String, Integer> df = new HashMap<String, Integer>();
		
		Set<String> overrideKeys = new HashSet<String>();
		
		int maxF = 1;
		for(String s : docs) {
			HashMap<String, Integer> dtf = new HashMap<String, Integer>();
			// count in one docs
			for(String term : TKAnalyzerTool.getInstance().parse(s)) {
				if(term.length() <= 1){
				    continue;
				}
				
				String override = IF_StopWord.getInstance().override(term);
				if(override!=null)
					overrideKeys.add(override);
				term = IF_StopWord.getInstance().has(term);
				if(term == null)
					continue;
				
				if(dtf.containsKey(term)){
					int n = dtf.get(term) + 1;
					if(n > WEIGHT_PER_DOC)
						continue;
					dtf.put(term, n);
				} else {
					dtf.put(term, 1);
				}
			}
			// accumulate in all docs
			for(String term: dtf.keySet()){
				if(tf.containsKey(term)) {
					int n = tf.get(term) + dtf.get(term);
					tf.put(term, n);
				} else {
					tf.put(term, dtf.get(term));
				}
				if(tf.get(term) > maxF)
					maxF = tf.get(term);
			}
			
			// count how docs number
			for(String t2 : dtf.keySet()){
				if( df.containsKey(t2) ){
					df.put(t2, df.get(t2) + 1);
				}
				df.put(t2, 1);
			}
		}
		
		LinkedTerm[] bucket = new LinkedTerm[maxF * docs.size() +1];
		for(String key : tf.keySet()){
			int v = tf.get(key) * df.get(key);
			LinkedTerm pos = bucket[v];
			LinkedTerm cur = new LinkedTerm();
			cur.term = key;
			cur.next = pos;
			bucket[v] = cur;
		}
		
		String[] res = new String[topK];
		int i=0;
		int f=maxF*docs.size();
		while(f>=0) {
			LinkedTerm pos = bucket[f];
			while(pos != null ) {
				// check if t in the replace/stopword list.
				String t = IF_StopWord.getInstance().has(pos.term);
				if( t != null )
					if(overrideKeys.contains(t))
						t=null;
				
				if( t != null )
					res[i++] = t;
				pos = pos.next;
				if(i==topK)
					break;
			}
			f--;
			if(i==topK)
				break;
		}
		return res;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
	    
	    String doc = "Struts2并非继承自Struts1的构架机制，而是继承自WebWork，因为Struts2是对WebWork的升级而产生的，Struts2吸收了Struts1和WebWork两者的优势，从而在稳定性、性能等各方面都有很好的保证。  Apache Struts2即使之前的WebWork2，经历几年的发展后WebWork和Struts2社区决定合并，也即是现在的Struts2。  Struts1 和 Struts2 的不同  Action 类：  Struts1要求Action类继承一个抽象基类，    Struts1 的普遍问题是使用抽象类编程而不是接口     Struts2 Action类可以实现一个Action接口，也可实现其他接口使可选和定制的服务成为可能。Struts2提供一个ActionSupport基类去实现常用接口，Action接口不是必须的，任何有execute标识的POJO对象都可以用作Struts2的Action对象  注：POJO：Plains and old Java Object 普通的Java类，不继承自特殊类，包含但不限於JavaBean  Struts1 和 Struts2 的不同  线程模式:    Struts1 Action是单例模式并且必须是线程安全的，因为仅有Action的一个实例来处理所有的请求。单例策略限制了Struts1 Action能作的事，并且要在开发时特别小心。Action资源必须是线程安全的或同步的。    Struts2 Action对象为每一个请求产生一个实例，因此没有线程安全问题。  Struts1 和 Struts2 的不同 Servlet 依赖： Struts1Action依赖于Servlet API因为Action被调用时HttpServletRequest和HttpServletResponse被传递给execute方法。  Struts2 Action不依赖于容器，允许Action脱离容器单独被测试。如果需要Struts2 Action仍然可以访问初始的request和response。但是，其他元素减少或者消除了直接访问HttpServletRequest和HttpServletResponse的必要性  Struts1 和 Struts2 的不同  捕获输入： Struts1 使用ActionForm对象捕获输入。所有的ActionForm必须继承一个基类。因为其他JavaBean不能用作ActionForm，开发者经常创建多余的类捕获输入。  Struts2 直接使用Action属性作为输入属性，消除了对第二输入对象的要求。Action属性能够通过web页面上的taglibs访问。  Struts2也支持AcionForm模式。  Struts1 和 Struts2 的不同  类型转换： Struts1 ActionForm属性通常是String类型。Struts1使用Commons-Beanutils进行类型转换。每个类一个转换器，对每个实例来说是不可配置的。  Struts2使用OGNL进行类型转换。提供基本和常用对象的转换器。  校验： Struts1 支持在ActionForm的validate方法手动校验，或者通过Commons Validateor的扩展来校验。同一个类可以有不同的校验内容，但不能校验子对象。  Struts2 支持通过validate方法和Xwork校验框架来进行校验。Xwork叫要框架使用为属性类类型定义的校验和内容校验，来支持chain校验子属性  XWork  实现独立运行的命令模式框架：  Action作为命令对象存在于XWork中。  添加了高级功能  拦截器      包括设置表单数，处理文件上传等  结果      包括多个Action之间的连接，转向表示层  简单的IoC（或者DI，依赖注入）容器，可对Action对象注入值（Spring）  强大的对象查询语言-OGNL  自动类型转换  元数据驱动的验证框架  插件机制  Struts2 基础包 struts2-core-2.0.11.jar       Struts2框架的核心类库     commons-logging-1.0.4.jar       Struts2框架日志包     freemaker-2.3.8.jar       Struts2的UI标签的模板     ognl-2.6.11.jar       对象图导航语言包     xwork-2.0.4.jar       XWork类库，struts2基于此构建  注：  加入所有jar包后项目无法启动：  struts2基于插件机制，插件载入时会自动实例化需要的类库，此时会出错。  Struts Core  封装HTTP请求 / 响应对象为Map     处理Sessions/Application范围     ServletDispatcher转换HTTP请求执行Action     提供了Web层的拦截器     将Result转换为具体的redirect&dispatch,或者其他表示层          Struts2 的系统架构图 M  V  C MVC是Model－View－Controller的简写。 Model  代表的是应用的业务逻辑（通过JavaBean，EJB组件实现），  View  是应用的表示面（由JSP页面产生）， Controller  是提供应用的处理过程控制（一般是一个Servlet），通过这种设计模型把应用逻辑，处理过程和显示逻辑分成不同的组件实现。这些组件可以进行交互和重用。从而弥补了Model 1的不足。  *Model 1: JSP页面中可以非常容易地结合业务逻辑(jsp:useBean)、服务端处理过程（jsp:scriplet）和HTML()，在JSP页面中同时实现显示，业务逻辑和流程控制，从而可以快速地完成应用开发。现在很多的Web应用就是由一组JSP页面构成的。这种以JSP为中心的开发模型我们可以称之为Model 1。  在Struts2中  Controller:FilterDispatcher  Model:Action，Interceptors  Struts2 流程 一个请求在Struts 2框架中的处理大概分为以下几个步骤。  客户端提交一个（HttpServletRequest）请求，如上文在浏览器中输入 http://localhost: 8080/bookcode/ch2/Reg.action就是提交一个（HttpServletRequest）请求。  请 求被提交到一系列（主要是3层）的过滤器（Filter），如（ActionContextCleanUp、其他过滤器（SiteMesh等）、 FilterDispatcher）。注意：这里是有顺序的，先ActionContext CleanUp，再其他过滤器（Othter Filters、SiteMesh等），最后到FilterDispatcher。  FilterDispatcher是控制器的核心，就是MVC的Struts 2中实现控制层（Controller）的核心。     Struts2 流程  FilterDispatcher询问ActionMapper是否需要调用某个Action来处理这个（HttpServlet Request）请求，如果ActionMapper决定需要调用某个Action，FilterDispatcher则把请求的处理交给ActionProxy。  ActionProxy通过Configuration Manager（struts.xml）询问框架的配置文件，找到需要调用的Action类。例如，用户注册示例将找到UserReg类。  ActionProxy创建一个ActionInvocation实例，同时ActionInvocation通过代理模式调用Action。但在调用之前，ActionInvocation会根据配置加载Action相关的所有Interceptor（拦截器）。  一旦Action执行完毕，ActionInvocation负责根据struts.xml中的配置找到对应的返回结果result。";
		
		String[] term = Tool.tf(doc, 40);
		System.out.println(StringUtils.join(term, "\t"));

	}
}
