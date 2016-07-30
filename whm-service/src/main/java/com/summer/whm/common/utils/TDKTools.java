package com.summer.whm.common.utils;

import com.summer.whm.service.analyzer.tf.IKAnalyzerService;

public class TDKTools {

    public static TDKModel getTDK(TDKModel model) {
        String tempExcerpt = JsoupUtils.plainText(model.getContent());
        String keywords = null;
        String description = null;
        if (tempExcerpt != null) {
            if (tempExcerpt.length() > 100) {
                keywords = IKAnalyzerService.getTopTerm(tempExcerpt);
                if (keywords == null) {
                    keywords = model.getTitle();
                }
                keywords = keywords + "," + model.getTagName();
            } else {
                keywords = model.getTagName() + "," + model.getTitle();
            }

            if (tempExcerpt.length() > 200) {
                description = tempExcerpt.substring(0, 180) + "...";
            } else {
                description = tempExcerpt;
            }
        } else {
            keywords = model.getTagName() + model.getTitle();
            if (model.getContent().length() > 180) {
                description = model.getContent().substring(0, 180);
            } else {
                description = model.getContent();
            }
        }

        model.setKeywords(keywords);
        model.setDescription(description);
        return model;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {

    }

}
