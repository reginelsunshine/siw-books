document.addEventListener('DOMContentLoaded', function () {
    const authorSearchInput = document.getElementById('authorSearch');
    const authorSuggestionsDiv = document.getElementById('authorSuggestions');
    const selectedAuthorIdInput = document.getElementById('selectedAuthorId');

    authorSearchInput.addEventListener('input', function () {
        const query = authorSearchInput.value.trim();
        if (query.length < 2) {
            authorSuggestionsDiv.innerHTML = ''; // Clear suggestions if query is too short
            return;
        }

        fetch(`/api/authors/search?keyword=${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                authorSuggestionsDiv.innerHTML = ''; // Clear previous suggestions
                data.forEach(author => {
                    const suggestionDiv = document.createElement('div');
                    suggestionDiv.textContent = `${author.name} ${author.surname}`;
                    suggestionDiv.addEventListener('click', function () {
                        authorSearchInput.value = suggestionDiv.textContent;
                        selectedAuthorIdInput.value = author.id;
                        authorSuggestionsDiv.innerHTML = ''; // Clear suggestions after selection
                    });
                    authorSuggestionsDiv.appendChild(suggestionDiv);
                });
            })
            .catch(error => {
                console.error('Errore nella ricerca degli autori:', error);
                authorSuggestionsDiv.innerHTML = '<div>Errore nella ricerca</div>';
            });
    });
});
