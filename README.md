# Castone Design
### 공공 데이터를 활용한 개인 맞춤형 주거지역 추천 서비스
1692149 정창하, 1771261 최연지, 1871386 유정윤, 1871395 이나현

<br>

### 1. 프로젝트 수행 목적
#### 1.1 프로젝트 정의
* 공공 데이터를 활용한 개인 맞춤형 주거지역 추천 서비스

#### 1.2 프로젝트 배경
* 도시의 편의시설, 교통 등이 발전함에 따라서 사람들은 주거를 선택할 때 단순히 집뿐만 아니라 주거지의 인프라 또한 중요한 부분으로 여기게 되었다.
* 주거환경 만족도는 사회적 지속 가능성의 만족도를 높이며 다양한 사회적 문제를 해결하고 공동의 관심과 공동의 가치관을 형성한다. 그 결과 우리 동네라는 인식을 만들어 그곳에 살고 있는 거주자들의 주관적 행복감 및 삶의 질을 높일 수 있다. 앞선 내용을 바탕으로 해당 프로젝트에서는 사용자들이 자신의 관심과 가치관에 맞추어 자신에게 맞는 동네를 찾을 수 있도록 한다.
*  공공 데이터 내 통계자료를 사용하여 자연, 안전, 교통, 복지 등의 카테고리를 세분화한 조건을 제공하고, 사용자가 주거지 선택 시 중요하게 생각하는 조건을 선택 하면 그를 바탕으로 적절한 주거지역을 분석하여 사용자 맞춤 주거지를 추천해 준다. 또한 지도를 통해 주거 지역의 지도를 제공하며, 거주자의 후기를 다른 사용자와 함께 공유가 가능하다.

#### 1.3 프로젝트 목표
* 지도를 통해 구역 설정
  * 지도 위에 구 단위의 지역 설정
* 구역별 데이터 수집 및 통계자료 제공
  * 설정된 지역의 데이터 수집 및 통계자료 제공
* 개인 맞춤형 주거지역 추천
  * 사용자 개인의 조건에 부합하는 주거지역 추천 및 그에 따른 데이터 시각화
* 추천 주거지역 실거주자 후기 게시판
  * 추천된 지역의 실거주자로부터 후기를 받아 사용자 간의 주관적인 의견 공유


### 2. 프로젝트 개요
#### 2.1 프로젝트 설명
* 편의시설, 교통, 안전 등 주거지역 지표에 대한 데이터를 수집 후, Anaconda의Panda를 통해 수집한 데이터를 구조화 및 편집하여 Firestore Database에 업데이트한다.
*  사용자가 선택한 주거지역 지표를 바탕으로 해당 지표를 분석하여 지표들의 평균을 계산하고, 그 값이 가장 높은 지표에 해당하는 상위 3개의 지역을 추천한다. 각 결과에 대해 지도를 함께 제공하며, 상세 결과를 통해 조회된 통계 데이터를 레이더 차트와 막대 그래프로 시각화하여 제공한다. 레이더 차트는 5개의 카테고리별로 결과 지역과 전체 평균 점수를 비교하여 결과 지역의 특성을 파악할 수 있으며, 막대 그래프는 선택한 지표들 각각에 대해 상위 7개의 구를 조회할 수 있다.
*   또한 게시판을 통해 사용자들 간의 거주 후기를 공유할 수 있다.

#### 2.2 프로젝트 구조

