package kr.hs.emirim.parksodam.breadclock.set;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("아니요. 사용자는 회원가입과 로그인이 따로 필요하지 않습니다.");

        List<String> football = new ArrayList<String>();
        football.add("네. 무료로 사용하실 수 있습니다.");


        List<String> basketball = new ArrayList<String>();
        basketball.add("빵클락은 생활 속 문제점을 개선하고 일상에 작은 행복을 담기 위한 앱으로, 식은 빵만 먹는 생활 속 문제점을 개선하고자 만든 빵 나오는 시간 알림앱입니다.");

        expandableListDetail.put("앱을 사용하려면 로그인이 필요한가요?", cricket);
        expandableListDetail.put("빵클락 앱은 무료인가요?", football);
        expandableListDetail.put("빵클락은 어떤 서비스인가요?", basketball);
        return expandableListDetail;
    }
}