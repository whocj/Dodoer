package com.summer.whm.service.search.keyword.associate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.utils.FileUtils;
import com.summer.whm.common.utils.FilterCharacterUtil;
import com.summer.whm.common.utils.IOUtil;
import com.summer.whm.common.utils.PinUtil;

@Service
public class AssociateWordFileService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String exportFile() {

        String fileName = GlobalConfigHolder.BASE_PATH + "opt/whm/temp/AssociateWord.txt";

        File file = new File(fileName);
        if (file != null && file.isFile()) {
            FileUtils.deleteFile(file);
        }

        String sql = "select qt.questionTitle, 'Question' from ask_question_text qt " + " union all "
                + " select t.TITLE, 'Topic' from bbs_topic t " + " union all select title, 'Blog' from blog_post "
                + " into outfile '" + fileName + "' fields terminated by '" + GlobalConfigHolder.DEFAULT_DELIMITER
                + "' lines terminated by '\r\n'";

        jdbcTemplate.execute(sql);

        return fileName;
    }

    public String generalAssociateWord(String srcFile) throws IOException {
        BufferedReader br = IOUtil.getReader(srcFile, IOUtil.UTF8);

        OutputStream os = new FileOutputStream(AssociateWordService.getAssociateWordFile());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, IOUtil.UTF8));

        String line = null;
        if (br != null) {
            StringBuffer sb = null;
            String keyword = null;
            while ((line = br.readLine()) != null) {
                sb = new StringBuffer();
                String[] strs = line.split(GlobalConfigHolder.SPLIT_DEFAULT_DELIMITER);
                if (strs.length >= 2) {

                    keyword = FilterCharacterUtil.filterCharacter(strs[0]);

                    sb.append(PinUtil.getPYHeadCharByCn(keyword)).append(GlobalConfigHolder.DEFAULT_DELIMITER)
                            .append(PinUtil.getPyByCn(keyword)).append(GlobalConfigHolder.DEFAULT_DELIMITER)
                            .append(keyword).append(GlobalConfigHolder.DEFAULT_DELIMITER).append(strs[1])
                            .append(GlobalConfigHolder.DEFAULT_DELIMITER).append(0)
                            .append(GlobalConfigHolder.DEFAULT_DELIMITER).append(0)
                            .append(GlobalConfigHolder.DEFAULT_DELIMITER);
                    sb.append("\r\n");
                    bw.write(sb.toString());
                }
            }
        }

        bw.close();
        os.close();
        br.close();

        return null;
    }

    public void initFile() {
        String srcFile = exportFile();
        try {
            generalAssociateWord(srcFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
