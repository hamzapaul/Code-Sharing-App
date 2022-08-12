# Code-Sharing-App

<h1>OverView</h1>

<p>
With this app you can share your code with your team-mates. You can also set the timer and specify the number of views so that after a certain period of time or when the maximun number of views will reach, it will get deleted from the database.

<h2>Tools</h2>
<ol>
   <li>Java8+ + HTML5 + CSS + JS</li>
   <li>Sring Boot</li>
   <li>H2 Database</li>
   <li>Spring Data JPA + Hibernate</li>
   <li>Gradle</li>
   <li>POST Man For testing</li>
</ol>
</p>


<h2>Endpoints</h2>
<ol>
   <h3>Web Interface = User Interface</h3>
   <li>POST: http://localhost:8889/code/new </li>
   <li>GET: http://localhost:8889/code/{uuid}</li>
   <li>GET: http://localhost:8889/code/latest</li>
   <h3>Api Interface = RESTFul Service</h3>
   <li>POST: http://localhost:8889/api/code/new </li>
   <li>GET: http://localhost:8889/api/code/{uuid}</li>
   <li>GET: http://localhost:8889/api/code/latest</li>
</ol>

