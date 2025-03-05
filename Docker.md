Conatiner란?
- 애플리케이션과 운영환경이 모두 들어있는 독립된 공간
- 애플리케이션 가상화
    - 경량의 독립 실행형 소프트웨어 패키지
    - 코드, 런타임, 시스템 도구, 시스템 라이브러리 및 설정과 같이 응용 프로그램을 실행하는 데 필요한 모든 것을 포함

- DOCKER_HOST의 Docker daemon에 명령(ex: docker build, docker pull, docker run)을 전달합니다.
- docker pull을 하면 Docker daemon이 Registry에서 필요한 컨테이너 이미지를 다운로드 받습니다.
- docker run하면 다운로드 받은 이미지를 실행시킵니다.
- docker push하면 Registry로 업로드 합니다.

Container Registry
- 컨테이너 이미지가 보관된 저장소
- 컨테이너 이미지는 읽기 전용

1. Public Registry : 누구나 이용할 수 있는 공개된 레지스트리
    - Docker Hub: hub.docker.com
    - Redhat Quay: quay.io
    - AWS: gallery.ecr.aws
2. Cloud Registry : CSP가 제공하는 레지스트리
    - Amazon ECR, Azure container registry, GCP Artifact Registry, NHN Container Registry 등
3. Private Registry : 온프레미스 환경으로 사내에서 운영할 수 있는 오픈소스 레지스트리
    - Harbor
    - GitLab Container Registry
    - docker registry

Container Image
- 컨테이너 실행을 위한 기반을 제공하는 읽기전용 템플릿 (READ ONLY)
- 읽기 전용의 컨테이너 템플릿
- 애플리케이션 실행환경, 소스, runtime이 포함된 독립된 컨테이너 애플리케이션
- /var/lib/docker/image
- /var/lib/docker/overlay2

이미지 관리
- docker [OPTIONS] COMMAND
- 컨테이너 이미지 관리 명령어
    - 이미지 검색 : `docker search`
    - 이미지 다운로드 : `docker pull`
    - 이미지 목록보기 : `docker images`
    - 이미지 히스토리 보기 : `docker history`
    - 이미지 세부 정보 보기 : `docker inspect`
    - 이미지 삭제 : `docker rmi`
- 컨테이너 관리 명령어
    - 컨테이너 생성 : `docker create`
    - 컨테이너 실행 : `docker start`
    - 컨테이너 종료 : `docker stop`
    - 컨테이너 강제 종료 : `docker kill`
    - 컨테이너 삭제 : `docker rm`
    - 컨테이너 실행 : `docker run`
    - 컨테이너 목록보기 : `docker ps`
    - 컨테이너 세부 정보 확인 : `docker inspect`
- 동작 중인 컨테이너 관리
    - `docker [OPTIONS] COMMAND`
    - `docker exec`
    - `docker top`
    - `docker logs`
    - `docker cp`
    - `docker diff`
- 컨테이너 저장소(registry) 관리
    - `docker build`
    - `docker tag`
    - `docker login`
    - `docker logout`
    - `docker save`
    - `docker load`
    - `docker export`
    - `docker import`

Container
- 컨테이너 이미지를 기반으로 생성되며 파일 시스템과 어플리케이션이 구체화되어 실행되는 하나의 프로세스
- 컨테이너는 하나의 프로세스
- 호스트 운영체제의 커널을 공유
- 컨테이너 내의 프로세스는 격리되어 운영

Container Image Layer
- 이미지는 한 개 이상의 불변(immutable)의 읽기전용 레이어의 집합인 유니언(union) 파일 시스템
- 가장 아래 Base image부터 블록 쌓듯이 레이어가 배치되어 overlay로 구현
- 컨테이너가 실행될 때 read/write 레이어가 상단에 배치되고 전체 레이어를 marge

> union mount : 하나인 것처럼 머지해서 보여줌.

도커 장점
- Application의 개발과 배포가 편해짐
  - 독립된 개발환경 보장
- 여러 Application의 독립성과 확장성이 높아짐
- 동일한 환경을 쉽게 재현 가능
- 개발, 테스트, 배포 환경을 일관성 있게 유지