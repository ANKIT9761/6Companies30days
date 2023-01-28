// link- https://leetcode.com/problems/most-popular-video-creator/

class Solution {
    
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, ViewsCounter> totalCounter = new HashMap<>();
        //build map
        for(int i=0; i<ids.length; i++){
            totalCounter.putIfAbsent(creators[i], new ViewsCounter());
            totalCounter.get(creators[i]).addVideo(ids[i], views[i]);
        }

        List<List<String>> result = new ArrayList<>();
        long nViews = 0;

        for(var entry: totalCounter.entrySet()){
            //we found a new more popular user
            if(entry.getValue().total>nViews){
                nViews = entry.getValue().total;
                result.clear(); // we clear the users found so far, since our standard is higher now
                result.add(List.of(entry.getKey(), entry.getValue().mostPopularVideoId));
            }
            // we found and equally famous user
            else if(entry.getValue().total==nViews){
                result.add(List.of(entry.getKey(), entry.getValue().mostPopularVideoId));
            }
        }

        return result;

    }

    class ViewsCounter{
        long total = 0;
        String mostPopularVideoId="";
        int videoViews = -1;

        ViewsCounter(){}

        void addVideo(String videoId, int nViews){
            //update total
            total=total+nViews;
            if(nViews==videoViews){
                //make sure to keep the smallest
                if(mostPopularVideoId.compareTo(videoId)>0)
                    mostPopularVideoId=videoId;
            } else if(nViews>videoViews){
                //we found a new more popular video, keep this one instead
                mostPopularVideoId = videoId;
                videoViews = nViews;
            }
        }
    }
}
