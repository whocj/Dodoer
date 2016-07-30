package com.summer.whm.service.search.core;

import java.util.Arrays;
import java.util.Collection;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;

import com.summer.whm.common.model.MapContainer;
import com.summer.whm.service.search.model.DocType;
import com.summer.whm.service.search.model.PostType;

public class DocConverter {

    private DocConverter() {
    }

    public static MapContainer convert(Document obj) {
        MapContainer mc = new MapContainer();
        for (IndexableField field : obj.getFields()) {
            mc.put(field.name(), field.stringValue());
        }

        return mc;
    }

    public static MapContainer convert(Document obj, String... filters) {
        return convert(obj, Arrays.asList(filters));
    }

    public static MapContainer convert(Document obj, Collection<String> filters) {
        MapContainer mc = new MapContainer();
        for (IndexableField field : obj.getFields()) {
            if (filters.contains(field.name())) {
                continue;
            }
            mc.put(field.name(), field.stringValue());
            
            if (DocType.DOC_FIELD_TYPE.equals(field.name()) && PostType.POST_URL_PREFIX.get(field.stringValue()) != null) {
                mc.put(DocType.DOC_FIELD_URL, String.format(PostType.POST_URL_PREFIX.get(field.stringValue()), obj.get(DocType.DOC_FIELD_OBJID)));
            }
        }

        return mc;
    }

    public static void main(String[] args){
        System.out.println(String.format("/ask/question/singleIndex/%s.html", "1"));
    }
}
