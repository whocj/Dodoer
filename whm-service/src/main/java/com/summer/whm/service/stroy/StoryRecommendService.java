package com.summer.whm.service.stroy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.mapper.story.StoryInfoMapper;

@Service
public class StoryRecommendService {

    private int QUERY_COUNT = 50;

    @Autowired
    private StoryInfoMapper storyInfoMapper;

    @Cacheable(value = "eternalCache", key = "#categoryId + '@' + 'StoryRecommendService.queryHotByCategoryId'")
    public List<StoryInfo> queryHotByCategoryId(Integer categoryId) {
        List<StoryInfo> allList = new ArrayList<StoryInfo>();
        allList.addAll(storyInfoMapper.queryTopHot(null, QUERY_COUNT));
        allList.addAll(storyInfoMapper.queryTopLike(null, QUERY_COUNT));
        
        return queryRandomN(allList, 8);
    }

    @Cacheable(value = "eternalCache", key = "#categoryId + '@' + 'StoryRecommendService.queryNewByCategoryId'")
    public List<StoryInfo> queryNewByCategoryId(Integer categoryId) {
        List<StoryInfo> allList = storyInfoMapper.queryLatestTopN(QUERY_COUNT * 10);
        return queryRandomN(allList, 8);
    }

    public List<StoryInfo> queryRandomN(List<StoryInfo> list, int n) {
        if (list != null && list.size() > 0) {
            List<StoryInfo> retList = new ArrayList<StoryInfo>();
            if (n >= list.size()) {
                return list;
            }
            Set<Integer> intSet = new HashSet<Integer>();
            int temp = 0;
            for (int i = 0; i < n; i++) {
                temp = RandomUtils.nextInt(list.size());
                if (!intSet.contains(temp)) {
                    intSet.add(temp);
                }
            }

            Iterator<Integer> intIterator = intSet.iterator();
            while (intIterator.hasNext()) {
                retList.add(list.get(intIterator.next()));
            }

            return retList;
        }
        
        return null;
    }
}
