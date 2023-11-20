package DFS;

import java.util.ArrayList;
import java.util.Collections;
/*
어려웠던 점
DFS의 완료 조건을 어떻게 설정해야 되는지
    일단 DFS로 모든요소를 탐색하되, 조건에 맞는 경우가 있을 시 정답으로 설정했어야 됐다.
    또한, 문자순으로 가장 앞선 정답을 선택해야 되었기에, 모든 경로들을 저장한 뒤,
    Collections.sort로 정렬함이 옳았다.
 */

public class 여행경로 {

    class Solution {
        public class ticket {
            public String start;
            public String end;
            public boolean visited = false;

            public ticket(String start, String end) {
                this.start = start;
                this.end = end;
            }
        }

        public ArrayList<String> answerRoute = new ArrayList<>();
        public String[] answer;
        public ArrayList<ticket> ticketArrayList = new ArrayList<>();

        public String[] solution(String[][] tickets) {
            for (String[] strings : tickets) {
                ticketArrayList.add(new ticket(strings[0], strings[1]));
            }
            DFS("ICN", "ICN", 0);
            Collections.sort(answerRoute);
            answer=answerRoute.get(0).split(",");
            return answer;
        }

        public void DFS(String now, String route, int cnt) {
            if (cnt == ticketArrayList.size()) {
                answerRoute.add(route);
                return;
            }
            for (ticket ticket : ticketArrayList) {
                if (ticket.start.equals(now) && !ticket.visited) {
                    ticket.visited = true;
                    DFS(ticket.end, route + "," + ticket.end, cnt + 1);
                    ticket.visited = false;
                }
            }
        }
    }
}
