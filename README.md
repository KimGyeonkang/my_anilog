<div align="center">
  <h1>My Anilog</h1>
</div>

<div align="center">
  <p>日本のアニメーションやカルチャーが好きな愛好家のためのコミュニティWebサービス</p>
</div>

<h2>プロジェクト紹介</h2>
  <p>「My Anilog」は、もともと日本のアニメーションが好きで日本語の勉強を始めた、作成者本人(キム・ギョンガン／@KimGyeonkang)の些細な希望からスタートしました。 </p>
  <div align="center">
    <h3>私みたいにアニメーションやカルチャーが好きな人が自由に話し合えるスペースを自分の手で作成してみたいな！</h3>
  </div>
  <p>その考えをきっかけに、画面とDB設計からJSP/Servlet MVCに基づく会員機能とコミュニティ掲示板（BOARD）まで一貫して開発し、「本格アニメーション愛好家のためのコミュニティWebサービス」を実装しました。</p>
  
<h2>開発目的</h2>
  <p>1. IT教育機関で学習したWeb開発の技術を活かし、実際のブログやコミュニティに近いサービスの実装を経験</p>
  <p>2. Servlet MVCメカニズムの理解および実践</p>
  <p>3. Oracle DB連携およびデータ処理プロセスの理解および実践<</p>
  <p>4. 日本文化愛好家という実際のユーザーを想定し、User Experience(UX)に基づく画面と内容の設計</p>
  <p>5. 会員と一般ユーザーの機能分離の理解および実践</p>

<h2>技術スタック</h2>
  <table>
    <colgroup>
      <col width="30%">
      <col width="60%">
    </colgroup>
    <tr>
      <th>Operation System</th>
      <td>Windows 10, 11</td>
    </tr>
     <tr>
      <th>IDE</th>
      <td>Eclipse IDE 2024-09</td>
    </tr>
    <tr>
      <th>Web Server(WAS)</th>
      <td>Apache Tomcat 9.0</td>
    </tr>
    <tr>
      <th>DataBase</th>
      <td>Oracle 11g</td>
    </tr>
    <tr>
      <th>DataBase Tool</th>
      <td>SQL Developer</td>
    </tr>
    <tr>
      <th>Version Control</th>
      <td>Git, Github</td>
    </tr>
    <tr>
      <th>Backend</th>
      <td>Java 21, JSP/Servlet</td>
    </tr>
    <tr>
      <th>Frontend</th>
      <td>HTML, CSS, JavaScript</td>
    </tr>
    <tr>
      <th>Library</th>
      <td>JQuery, JSTL</td>
    </tr>
  </table>

<h2>DB設計</h2>
<h3><a href="https://sqltoerdiagram.com/#s=zfZPNbtpAEMdfZTQnQEuxDaSpezLgFlQDkWUqRaVCC96CFXvtrg0Nijg1ueXYQy-peskDVMoh7xTyDpU_EvMlLqvx_H__mdkd-QppEKCK9th2phQJLpgIHZ-jKhMMv7uoYqUEXeaNmYBSZcgrJXi-uV3fPTw9_oL1z7v19b8kPxGMRgwiOnYZeClfGHIAAMeGBRWTGRVKQa4XgfsR8LnrQiAcj4olXLAlSdGAhuEPX2wY3kq5YQcauYxPo1nOKnsopx7b0PdrRcwdyTlRPQgoOVA7CFSPAcyjzlaT-v4YKaMcHfUbXWwOSqBcXl__ff79uP5z_3T_kEKCTUd2vIj4yIzzIP7YS7NLJ8qTQ14c8vfJfhs-FfbRZTdNXbN0sLSGocM4wbNd9_rwWTObbc1UCvUi9PoW9AaGAWdmp6uZ5_BJP8_aWx3L0HNYlqQcz5Bmv2fpPSuHFOkApVmW1mxvV8qkdicmuw3djIdp6R-0gWGBlKmm_nHUaW0Y63u1Y6QVXzU5dsTBWZzdLbEtvppf3hcJ2g512SRCFQM_jKaChUji0Ikcn4eoXmH6_8TRJarVU4JLVMuKcroimLz2i1J_ld6tVgQn1GOCZqKcatXaCcFwQl2GqvxGqp1UVwQp535Es3ZfvhKcObbNeBp7lM-pazj8IhFX_wE">My Anilog</a></h3>

<h2>プロジェクト構成</h2>
<pre>
src/main
├─java
│  ├─command
│  │  ├─board 　 #コミュニティ掲示板
│  │  ├─index  　#メインページ
│  │  └─member   #会員
│  ├─controller   
│  ├─dao   
│  ├─dto   
│  └─mail        #追加機能：ユーザーのメールアドレスに新しいパスワード送信
└─webapp
    ├─attach
    ├─board 
    ├─css
    ├─js 　　　　　#共通のJavaScriptソース、jQuery Library
    ├─member   
    └─WEB-INF
        └─lib     #Java Library
</pre>
