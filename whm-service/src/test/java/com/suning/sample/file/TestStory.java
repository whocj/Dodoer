package com.suning.sample.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.summer.whm.Constants;

public class TestStory {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @throws IOException 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) throws IOException {
        String temp = "的是一位武者么？”<br/><br/> &nbsp;&nbsp;&nbsp;&nbsp;    沈绯云和霄雅两人对视了一眼。<br/><br/> &nbsp;&nbsp;&nbsp;&nbsp;    秦玉柔在临江市读大一，大部分时候和她姐姐生活在一起，和她那名义上的姐夫也是抬头不见低头见，居然会不知道王城是不是一位武者？<br/><br/> &nbsp;&nbsp;&nbsp;&nbsp;    到底是她们弄错消息了，还是……<br/><br/> &nbsp;&nbsp;&nbsp;&nbsp;";
        BufferedWriter bw = null;
        try {
            File file = new File(Constants.BASE_STORY_TEMP + "/" + 110 + ".txt");
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
            bw.write("#多多儿小说网(Dodoer)整理收藏 http://www.dodoer.com\n");
            bw.write("#本站TXT小说文本只供测试，不作商业用途，下载后请二十四小时后请删除\n");
            bw.write("#详细信息请查看http://www.dodoer.com/main/" + 11 + ".html\n");
            
            bw.write("\n");
            bw.write("《多多儿小说网》\n");
            bw.write("作者:夏天\n");
            bw.write("简介:衣衫褴褛的老人蹲坐在破败房子前的白桦木墩子上，喝一口自制的烧酒，抽一口极烈的青蛤蟆旱烟，眯起眼睛，望着即将落入长白山脉的夕阳，朝身旁一个约莫六七岁、正陪着一黑一白两头土狗玩耍的小孩子说道：“浮生，最让东北虎忌惮的畜生，不是皮糙肉厚的黑瞎子，也不是600斤的野猪王，而是上了山的守山犬。” 许多年后，老人躺进了一座不起眼的坟包，那个没被大雪天刮烟炮冻死、没被张家寨村民戳脊梁骨白眼死的孩子终于走出大山，\r\n");
            bw.write("\n");
            
            temp = temp.replace("<br>", "\n");
            temp = temp.replace("<br/>", "\n");
            temp = temp.replace("&nbsp;", "");

            bw.write(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                bw.flush();
                bw.close();
                bw = null;
            }
        }
    }

}
