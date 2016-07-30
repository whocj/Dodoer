package com.summer.whm.service.analyzer.tf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.summer.whm.service.analyzer.tf.common.Tool;

public class IKAnalyzerService {

    private static final int TF_WINDOW_SIZE = 100;

    public static String getTopTerm(String doc) {

        String[] topTerm = Tool.tf(doc, TF_WINDOW_SIZE);

        Set<String> context = new HashSet<String>();
        List<String> strList = new ArrayList<String>();
        context.addAll(Arrays.asList(topTerm));
        String temp = null;
        for (String str : context) {
            temp = ITLibraryTool.getInstance().getTerm(str);
            if (temp != null) {
                strList.add(temp);
            }
        }

        if (strList != null && strList.size() > 0) {
            return StringUtils.join(strList, ",");
        }
        return "";
    }

    public static void main(String[] args) {
        String doc = "使用VMVare组建局域网测试MSMQ（一） 桥接模式搭建VMVare虚拟机        MSMQ与客户端在同一服务器的情况下使用非常简单，由于要分布式部署，而测试机有限，所以在本机使用VMware搭建局域网测试MSMQ的使用。本篇主要分两部分，第一部分讲解VMware虚拟机的搭建，第二部分讲解远程MSMQ的使用。原理性的知识请需要的同学自行搜索园内相关文章，这里着重描述一下使用方法。        项目的使用场景是分布式部署，并且是在局内网内，为了模拟生产环境，使用桥接模式搭建VMware虚拟机，虚拟机操作系统为WINDOWS SERVER 2008 R2。为什么使用桥接模式而不是更简单的NAT模式呢？答案是本人在NAT模式下，宿主机无法与虚拟机通信。具体原因，还请看到这篇文章的大牛指点迷津。废话不多说，进入正题，桥接模式搭建VMVare Step By Step。     配置宿主机 1. 注：宿主机使用Window10系统，无线网卡。打开 【控制面板所有控制面板项网络和共享中心更改适配器配置】，右键查看【无线网络连接】【属性】，勾选【VMware Bridge Protocol】。       2. 在【共享】选项卡中，配置共享，如下图       3. 查看宿主机网络的[IPv4地址】【IPv4子网掩码】【IPV4默认网关】，以供后续配置VMware网络使用，见下图。注意：如果使用有线连接，IP地址，默认网关会与无线连接不同，但后续配置VMVare网路的方式相同，只需配置成与宿主机一致的网段，具体配置见后面的讲解。       4. 开启宿主机【DHCP Client】和【DNS Client】服务          配置VMware 1. 前面的几个步骤把宿主机已经配置好了，现在开始配置VMware。第一步配置VMware使用桥接模式，打开【虚拟机】【设置】【网络适配器】，【网络连接】勾选【桥接模式】       2. 打开【编辑】【虚拟网络编辑器】，勾选【桥接模式】，选择【宿主机已经联网的网卡】。注：本机使用的是无线网卡       3. 配置虚拟机IP地址，子网掩码，默认网关和DNS。打开虚拟机【控制面板网络和Internet网络连接】，右键查看【本地连接】【属性】，进行配置。注意：虚拟机的IP地址要与宿主机的IP地址在同一网段，虚拟机的子网掩码要与宿主机的子网掩码相同，虚拟机的默认网关要与宿主机的默认网关相同，虚拟机的DNS与虚拟机（此处不是宿主机）的默认网关相同。          4. 开启宿主机上VMware相关的几个服务，如图       5. 关闭虚拟机防火墙，这一步非常重要，不然宿主机不能访问虚拟机。打开【控制面板所有控制面板项Windows 防火墙自定义设置】，进行配置，如图";
        System.out.println(IKAnalyzerService.getTopTerm(doc));
        // List<String> strList = LibraryTool.getInstance().getTerm(doc);
        // System.out.println(strList);
    }

}
