<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Загрузка PDF</title>
    <link rel="stylesheet" href="/css/sss.css">

</head>
<body>
    <div class="title">Welcome to ReFind :)</div>

    <div class="navbar">
        <a href="#home">Главная</a>
        <a href="#upload">Загрузка</a>
        <a href="#about">Резюме</a>
        <a href="#contact">Дополнительно</a>
    </div>

 <div class="navigation" id = "navigation">

                    <h1>Поисковая система</h1>

              <form method="get" action="/">
                    <input type="text" name="keyword" placeholder="Введите ключевое слово" />
                    <button type="submit">Поиск</button>
                    <br>
                </form>

  <div class="card-container">
        <div class="card">
            <p><strong>Имя:</strong> Иван Иванов</p>
            <p><strong>Контактная информация:</strong> ivan.ivanov@email.com <br> +7 (999) 123-45-67</p>
            <p><strong>Должность:</strong> Разработчик программного обеспечения</p>
            <p><strong>Обязанности:</strong> Разработка и поддержка веб-приложений, тестирование, документирование кода</p>
            <p><strong>Навыки:</strong> Java, Spring Boot, SQL, REST API</p>
            <p><strong>Языки:</strong> Русский, Английский</p>
            <p><strong>Образование:</strong> Московский государственный университет, Информатика и вычислительная техника</p>
        </div>

<div class="card">
    <p><strong>Имя:</strong> Жанар Бекетова</p>
    <p><strong>Контактная информация:</strong> janar.beketova@example.kz <br> +7 (701) 987-65-43</p>
    <p><strong>Должность:</strong> Аналитик</p>
    <p><strong>Обязанности:</strong> Анализ данных для стратегических решений, создание отчетов, прогнозирование на основе данных</p>
    <p><strong>Навыки:</strong> Аналитика данных, Excel, SQL</p>
    <p><strong>Языки:</strong> Казахский, Русский</p>
    <p><strong>Образование:</strong> Назарбаев университет, бакалавр математики</p>
</div>

        <!-- Дополнительные карточки при необходимости -->
        <div class="card">
            <p><strong>Имя:</strong> Ольга Смирнова</p>
            <p><strong>Контактная информация:</strong> olga.smirnova@email.com <br> +7 (999) 234-56-78</p>
            <p><strong>Должность:</strong> Аналитик данных</p>
            <p><strong>Обязанности:</strong> Анализ данных, создание отчетов, визуализация данных</p>
            <p><strong>Навыки:</strong> Python, SQL, Tableau, Power BI</p>
            <p><strong>Языки:</strong> Русский, Английский</p>
            <p><strong>Образование:</strong> Санкт-Петербургский университет, Экономика</p>
        </div>
    </div>
    </div>

    <div class="container">
        <h1>Загрузка PDF</h1>

        <!-- Первая форма для загрузки PDF -->
        <form id="pdfUploadForm" class="center-form">
            <label for="pdfFile">Выберите файл PDF</label>
            <input type="file" id="pdfFile" name="pdfFile" accept="application/pdf" required hidden onchange="PDFupdateFileName()">
            <label for="pdfFile" class="custom-file-labelP">
                <img src="https://trashbox.ru/apk_icons/1936325_512.png" class="file-upload-icon" alt="Загрузка файла">
            </label>
            <button type="submit">Загрузить PDF</button>
        </form>

         <p id="pdfResponseMessage"></p>


    <!-- Вторая форма для загрузки ZIP -->
       <h1>Загрузка ZIP</h1>

       <form id="zipUploadForm" class="center-form">
           <label for="zipFile">Выберите ZIP файл с PDF</label>
           <input type="file" id="zipFile" name="zipFile" accept=".zip" required hidden onchange="ZIPupdateFileName()">
           <label for="zipFile" class="custom-file-labelZ">
               <img src="https://www.shareicon.net/data/2016/05/06/760836_document_512x512.png" class="file-upload-icon" alt="Загрузка файла">
           </label>
           <button type="submit">Загрузить ZIP</button>
       </form>

        <p id="zipResponseMessage"></p>
   </div>
    <div class="footer">&copy; 2024 Автор: team 40</div>

 <script>
     // Обработчик для загрузки ZIP
     document.getElementById('zipUploadForm').addEventListener('submit', async (event) => {
         event.preventDefault(); // Отключаем стандартное поведение формы

         const fileInput = document.getElementById('zipFile');
         const formData = new FormData();
         formData.append('zipFile', fileInput.files[0]);

         try {
             const response = await fetch('/api/pdf/upload-zip', {
                 method: 'POST',
                 body: formData
             });

             if (response.ok) {
                 const message = await response.text(); // Получаем текст из ответа
                 document.getElementById('zipResponseMessage').innerText = "" + message;
                 document.getElementById('zipResponseMessage').style.color = "lime";
             } else {
                 const errorText = await response.text();
                 document.getElementById('zipResponseMessage').innerText = "Ошибка: " + errorText;
                 document.getElementById('zipResponseMessage').style.color = "red";
             }

         } catch (error) {
             document.getElementById('zipResponseMessage').innerText = 'Ошибка при загрузке файла.';
             document.getElementById('zipResponseMessage').style.color = "red";
         }
     });

     // Обработчик для загрузки PDF
     document.getElementById('pdfUploadForm').addEventListener('submit', async (event) => {
         event.preventDefault(); // Отключаем стандартное поведение формы

         const fileInput = document.getElementById('pdfFile');
         const formData = new FormData();
         formData.append('pdfFile', fileInput.files[0]);

         try {
             const response = await fetch('/api/pdf/upload-pdf', {
                 method: 'POST',
                 body: formData
             });

             if (response.ok) {
                 const message = await response.text(); // Получаем текст из ответа
                 document.getElementById('pdfResponseMessage').innerText = "" + message;
                 document.getElementById('pdfResponseMessage').style.color = "cyan";
             } else {
                 const errorText = await response.text();
                 document.getElementById('pdfResponseMessage').innerText = "Ошибка: " + errorText;
                 document.getElementById('pdfResponseMessage').style.color = "red";
             }

         } catch (error) {
             document.getElementById('pdfResponseMessage').innerText = 'Ошибка при загрузке файла.';
             document.getElementById('pdfResponseMessage').style.color = "red";
         }
     });
 </script>
     <script>
         function PDFupdateFileName() {
             const fileInput = document.getElementById('pdfFile');
             const filer = document.querySelector('.custom-file-labelP');
             filer.textContent = fileInput.files.length > 0 ? fileInput.files[0].name : "Выберите файл";
         }
     </script>
     <script>
         function ZIPupdateFileName() {
             const fileInput = document.getElementById('zipFile');
             const filer = document.querySelector('.custom-file-labelZ');
             filer.textContent = fileInput.files.length > 0 ? fileInput.files[0].name : "Выберите файл";
         }
     </script>
</body>
</html>
