Getting Started with Gauge
==========================

This is an executable specification file. This file follows markdown syntax. Every heading in this file denotes a scenario. Every bulleted point denotes a step.
To execute this specification, use `mvn test`


QatarAirwaysTest
-----------------
* Uygulamanın açıldığı kontrol edilir
* Karşılama ekranı “Skip” seçeneği ile geçilir
* Alt menüden “Book” tabına geçiş yapılır
* Seyehat tipi olarak “One Way” seçilir
* Kalkış havaalanı olarak Berlin seçilir
* Varış havaalanı olarak Istanbul seçilir
* (Bugünün tarihi + 7 gün) olarak bir gidiş tarihi seçilir
* Economy class seçeneğine tıklanır
* Arama butonuna tıklanır
* Uçuş seçim ekranının geldiği kontrol edilir
* Rastgele bir uçuş seçilir.
* Uçuş seçim ekranındaki uçuş saati ile uçuş detayları ekranındaki uçuş saati değeri aynı mı kontrol edilir
