package Hash;

import java.util.*;

/*
장르별 가장 많이 재생된 노래 2개를 모아 베스트 앨범 출시
노래는 고유 번호로 구분
노래 수록 기준
    a. 속한 노래가 많이 재생된 장르 => 장르별 총 재생수
    b. 장르 내 가장 많이 재생된 노래
    c. 장르 내 재생 횟수가 같다면, 고유 번호가 가장 낮은 노래
고유 번호 = index
genres[i], plays[i] => 고유번호 i 노래의 장르와 재생횟수

풀이 방법
1. 장르별 재생 횟수의 총합을 비교해서 가장 많이 재생된 장르 순으로 넣음
2. 장르별 재생횟수가 가장 많은 2곡을 넣음

제한
노래 개수는 1~10,000
장르 개수는 1~99
 */
public class L3_PS42579_베스트앨범 {
    public class Music implements Comparable<Music> {
        String genre;
        int plays;
        int playSum;
        int index;

        public Music(String genre, int plays, int playSum, int index) {
            this.genre = genre;
            this.plays = plays;
            this.playSum = playSum;
            this.index = index;
        }

        @Override
        public int compareTo(Music o) {
            if (this.playSum != o.playSum) {
                return o.playSum - this.playSum;
            } else if (this.plays != o.plays) {
                return o.plays - this.plays;
            } else {
                return this.index - o.index;
            }
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i],map.getOrDefault(genres[i],0)+plays[i]);
        }
        List<Music> list = new ArrayList<>();
        for (int i = 0; i < genres.length; i++) {
            list.add(new Music(genres[i], plays[i], map.get(genres[i]), i));
        }
        Collections.sort(list);
        String curGenre = "";
        int cnt = 0;
        for (Music music : list) {
            if(!curGenre.equals(music.genre)){
                curGenre=music.genre;
                cnt=0;
            }
            if (cnt >= 2) {
                continue;
            }
            answer.add(music.index);
            cnt++;
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
