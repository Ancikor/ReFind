// Функция для загрузки файлов
function uploadFiles() {
    const fileInput = document.getElementById('file-upload');
    const files = fileInput.files;
    if (files.length === 0) {
        alert('Пожалуйста, выберите файлы для загрузки.');
        return;
    }
    // Для примера, просто выводим названия файлов
    const fileNames = Array.from(files).map(file => file.name).join(', ');
    alert('Загружены файлы: ' + fileNames);
}
// Функция для применения фильтров
function applyFilters(event) {
    event.preventDefault();
    const experience = document.getElementById('experience').value;
    const skills = document.getElementById('skills').value;
    const education = document.getElementById('education').value;
    // Для примера, выводим выбранные значения
    console.log(Опыт работы: $ {
            experience
        }
        лет);
    console.log(Навыки: $ {
        skills
    });
    console.log(Образование: $ {
        education
    });
    // После применения фильтров, например, можно показать результаты
    alert('Фильтры применены!');
}
// Функция для сброса фильтров
function resetFilters() {
    document.getElementById('filter-form').reset();
    alert('Фильтры сброшены.');
}
// Функция для перехода между секциями
function scrollToSection(sectionId) {
    const section = document.getElementById(sectionId);
    window.scrollTo({
        top: section.offsetTop - 70, // немного отступим для шапки
        behavior: 'smooth'
    });
}
// Обработчики событий для кнопок фильтров
document.getElementById('filter-form').addEventListener('submit', applyFilters);
document.getElementById('filter-form').addEventListener('reset', resetFilters);
// Пример кнопок перехода по секциям
document.getElementById('go-dashboard').addEventListener('click', () => scrollToSection('dashboard'));
document.getElementById('go-upload').addEventListener('click', () => scrollToSection('upload'));
document.getElementById('go-filters').addEventListener('click', () => scrollToSection('filters'));
document.getElementById('go-results').addEventListener('click', () => scrollToSection('results'));
document.getElementById('go-settings').addEventListener('click', () => scrollToSection('settings'));