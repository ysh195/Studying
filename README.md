1. Calculation
   
  코드 설명
  - String 타입의 완성된 계산식을 입력하면 String 타입의 결과값을 출력하는 계산기.
  - 사칙 연산과 () 연산이 가능하고, 실수 및 소숫점 연산이 가능합니다.
  - 학원에서 지시한 방식은 scanner로 숫자 2개와 연산자 1개를 입력 받고, switch문으로 계산하는 방식이었는데, 그건 도저히 계산기로 볼 수 없다고 생각해서 혼자서 남는 시간에 공부하여 작성함.

  개선점
  - 메인 클래스 하나가 너무 길다. 각 메소드를 모아 놓은 클래스를 하나 만들고 그걸 상속하는 방식을 써야 했나
  - String 타입으로 계산하느라 그랬지만, 코드가 길고 이해하기 어려움. 무려 450줄. 내가 쓴 거지만 나중에 보더라도 쉽지 않을 것 같음. 아직 코딩학원 3주차이기도 하고, 기본적인 메소드나 함수 사용이 미숙했기 때문이라고 생각
  - 버튼을 생성하는 과정이 단순한 것에 비해 너무 길다. 좀 더 단순하고 간단하게 하는 방법이 없나? 버튼 생성하는 생성자를 따로 만들어서 해결해야 하나?
  - swing을 사용하는 생성자로 메인에 연결했더니 버튼에 ActionListener가 연결이 안 됐음. 단순히 버튼 이름.ActionListener로 했었는데, 클래스 이름.버튼 이름.ActionListener로 해야 했나?
  - 버튼 입력값을 텍스트필드에 반영하는 방법으로 더 쉽고 간결한 방법은 없을까?
  - 계산식 중간에 while 빼먹어서 오류난 적 있음. 가장 안쪽에 있는 블록을 하나 떼어내고, while을 사용해서 떼어낸 블록 내의 계산식에 대한 계산을 모두 끝낸 다음 결과값을 리턴해야 하는데, while이 빠져서 블록 내의 계산이 다 끝나지도 않은 상태로 결과값을 리턴하니까 계산은 끝나지도 않고 블록만 사라짐. 오히려 사칙연산이 꼬이는 문제가 있어서 수정함.
  - 그리고 +-도 */처럼 뒤에서부터 계산하니까 70-9-9면, 9-9부터 계산해서 70-0이 나오는 문제가 있어서 수정함.
  - 실수 및 소숫점 연산이 가능하고, 그러한 계산이 가능하도록 구성하는 것에 신경을 썼음에도 실수로 "." 버튼을 넣지 않았음. 근데 추가하자니 21개의 버튼이라 어떻게 배치해야 할 지 난감해서 그냥 두기로 함.
  - 그리고 바로 이전의 배열에 저장된 것이 연산자 기호이고, 현재 판단 중인 것이 연산자 기호일 경우, 계산 불가능해지는 변수에 대해서 대비하지 않았음.
  - 계산 프로세스에 돌입해서 오류가 나기 전에, 배열에 저장하는 과정에서 [a. 해당 값을 배열에 저장하지 않고 넘어감으로써 맨 앞의 연산자만 취하거나, b. 바로 이전의 배열에 계속해서 덮어씌움으로써 맨 뒤의 연산자만 취하는] if문을 추가함으로써 대비할 수 있을 것임.
  - try{~}catch문이나 결과값이 오류일 때 무조건 null을 반환하는 메소드를 구성 및 사용해서 최대한 심각한 오류를 피하는 방식의 사용도 생각해야 함

    응용 방향
  - 이걸 응용해서 게시판 같이 하나의 창 안에 여러 개의 게시글제목(버튼)을 배치하고, 그 버튼을 누르면 해당 게시글에 해당하는 새로운 프레임을 띄우는 형식도 가능할 것 같음. 만약 ActionListener가 버튼뿐만이 아니라 단순한 텍스트에도 사용할 수 있다면 게시글 제목을 버튼으로 하지 않아도 될 것 같음
  - 이걸 응용해서 간단한 채팅방 같은 거 만들 수 있지 않을까?(완료)
  - 좀 더 난이도가 높겠지만 블랙잭도 만들 수 있을 것 같은데(완료 / 다른 레포지토리에 보관)

2. 채팅창
   - 그냥 계산기에서 구성요소의 위치, 사이즈나 텍스트 등만 바꾸고, 텍스트 라벨을 텍스트 에어리어로 바꾸면 끝이니까 쉬웠음.
   - 근데 다수의 프레임 내의 이벤트 간의 연계가 까다로움. 프레임 A 안에서 발생한 버튼 액션 이벤트가 프레임 B에도 영향을 줘야 하는데, 그게 쉽지 않음
   - 프레임 및 변수, 내부 메서드의 실행 시점과 버튼 액션이 발생하는 시점이 다르고, 서로 완전 별개의 클래스라서 그런 것으로 추측
3. 지뢰찾기
   - 지뢰 탐색 시 자동으로 주변이 탐색되는 범위 설정이 어려움.
   - 버튼에 표시되는 지뢰의 갯수를 어떻게 카운트해야 좋을 지 모르겠음.
   - 지뢰를 눌렀을 시 게임 종료되는 방식을 어떻게 해야 할 지 모르겠음 일단 system.exit으로 했는데, 텍스트라벨과 버튼에 표시한 텍스트는 안 나오고 그대로 프레임이 꺼짐. 그래서 할 수 없이 콘솔에 메시지 출력함
   - 지뢰가 어디 있을 지 추측하고, 거기에 깃발을 모두 꽂아서 승리해야 하는데, 뭘 어떻게 해야 클릭으로 인식되지 않고 깃발을 꽂았다고 표현할 수 있을 지 모르겠음.
   - 지뢰의 생성 갯수 설정을 안 함. 최소 몇 개는 만들어지도록 해야 하는데, 그냥 난수에 맡겨서 아주 낮은 확률로 지뢰가 존재하지 않는 경우도 있음. 이 부분은 솔직히 귀찮아서 안 한 것.
   - 지뢰찾기를 만들려고 했으면서 정작 지뢰찾기에 대한 이해도가 많이 떨어지고, 이걸 완벽하게 구현하기엔 실력이 부족했음.
