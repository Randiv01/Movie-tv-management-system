function toggleSection(sectionId, headerElement) {
    const section = document.getElementById(sectionId);
    const icon = headerElement.querySelector('.un-toggle-btn i');

    if (section.classList.contains('hidden')) {
        section.classList.remove('hidden');
        headerElement.classList.add('open');
        icon.classList.remove('fa-chevron-down');
        icon.classList.add('fa-chevron-up');
    } else {
        section.classList.add('hidden');
        headerElement.classList.remove('open');
        icon.classList.remove('fa-chevron-up');
        icon.classList.add('fa-chevron-down');
    }
}
