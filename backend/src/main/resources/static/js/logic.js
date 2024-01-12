window.onload = function() {
    fetchAndDisplayAuthors();
};

class AuthorData {
    constructor() {
        this.authors = [];
    }

    getAuthors() {
        return this.authors;
    }

    setAuthors(authors) {
        this.authors = authors;
    }
}

var authorData = new AuthorData()

function fetchAndDisplayAuthors(filter_by='', filter = '') {
    let url = 'http://localhost:8090/authors'

    if (filter && filter_by) {
        url += `/filter_by/${filter_by}/${filter}`; 
    }

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        //if (!response.ok) {
            //console.log(response.json())
            //if(response.status === 404) {
                //throw new Error(response.json().message);
            //} else {
                //throw new Error('Invalid request!');
            //}
            
        //}
        return response.json();
    })
    .then(response => {
        if(response.status !== 200) {
            throw new Error(response.message);
        }
        authors = response.response
        authorData.setAuthors(authors);
        updateTable(authors);
    })
    .catch((error) => {
        document.getElementById('result_table').innerHTML = error;
        //document.getElementById('result_table').innerHTML = '';
        //authorData.setAuthors([])
    });
}

function updateTable(authors) {
    const table = createTable(authors);
    document.getElementById('result_table').innerHTML = table;
}

function createTable(authors) {
    let table = '<table class="result_table"><tr class="table_header"><th class="table_header_cell">Author ID</th><th class="table_header_cell">First name</th><th class="table_header_cell">Last name</th><th class="table_header_cell">Date of birth</th><th class="table_header_cell">Date of death</th><th class="table_header_cell">Number of children</th><th class="table_header_cell">University</th><th class="table_header_cell">University location</th><th class="table_header_cell">Book ID</th><th class="table_header_cell">Book name</th><th class="table_header_cell">Release year</th></tr>';
    
    authors.forEach(author => {
        if(author.books.length === 0) {
            book = {}
            book.book_id = "/";
            book.book_name = "/";
            book.release_year = "/";
            author.books.push(book)
        }
        
        author.books.forEach(book => {table += `<tr>
        
        <td>${author.author_id}</td>
        <td>${author.first_name}</td>
        <td>${author.last_name}</td>
        <td>${convert_date(author.birth_date)}</td>
        <td>${convert_date(author.death_date)}</td>
        <td>${author.children_count}</td>
        <td>${author.university ? author.university.university_name : 'No'}</td>
        <td>${author.university ? author.university.country.country_name : 'N/A'}</td>
        <td>${book.book_id}</td>
        <td>${book.book_name}</td>
        <td>${book.release_year === 0 ? 'unknown' : book.release_year}</td>
      </tr>`;});
    } );
    table += '</table>';
    return table;
}

function applyFilter() {
    const filterValue = document.getElementById('filter_value').value;
    const filter_by = document.getElementById("filter_by").value;
    fetchAndDisplayAuthors(filter_by ,filterValue);
}

function clearFilters() {
    fetchAndDisplayAuthors();
    document.getElementById('filter_value').value = "";
    document.getElementById("filter_by").value = "";
}

function download_json() {
        authors = authorData.getAuthors();
        authors_json = []

        let person_context = {};
        person_context['@vocab'] = "http://schema.org/";
        person_context['author_id'] = "identifier";
        person_context['first_name'] = "givenName";
        person_context['last_name'] = "familyName";
        person_context['birth_date'] = "birthDate";
        person_context['death_date'] = "deathDate";

        let book_context = {}
        book_context['@vocab'] = "http://schema.org/";
        book_context['book_id'] = "identifier";
        book_context['book_name'] = "name";
        book_context['release_year'] = "copyrightYear";

        authors.forEach(author => {
            let new_author = {};

            let person = {}
            person['@context'] = person_context;
            person['@type'] = "Person";
            person['author_id'] = author.author_id;
            person['first_name'] = author.first_name;
            person['last_name'] = author.last_name;
            person['birth_date'] = convert_date(author.birth_date);
            person['death_date'] = convert_date(author.death_date);

            new_author['person'] = person;

            new_author['children_count'] = author.children_count;
            new_author['attended college'] = author.university ? author.university.university_name : 'No';
            new_author['college location'] = author.university ? author.university.country.country_name : 'N/A';

            new_author['bibliography'] = [];
            author.books.forEach(book => {
                let new_book = {}
                new_book['@context'] = book_context;
                new_book['@type'] = "Book";
                new_book['book_id'] = book.book_id;
                new_book['book_name'] = book.book_name;
                new_book['release_year'] = book.release_year;

                new_author['bibliography'].push(new_book);
            })

            authors_json.push(new_author);
        })
        download(JSON.stringify(authors_json), "data.json", "text/plain");
}

function download_csv() {
    authors = authorData.getAuthors();
    authors_csv = "Author ID,First Name,Last Name,Birth Date,Death Date,Children Count,Attended College,College Location,Book ID,Book Name,Release Year\n"
    authors.forEach(author => {
        let new_author = "";

        new_author += author.author_id + ",";
        new_author += author.first_name + ",";
        new_author += author.last_name + ",";
        new_author += convert_date(author.birth_date) + ",";
        new_author += convert_date(author.death_date) + ",";
        new_author += author.children_count + ",";
        new_author += (author.university ? author.university.university_name : 'No') + ",";
        new_author += (author.university ? author.university.country.country_name : 'N/A') + ",";

        author.books.forEach(book => {
            let new_book = ""

            new_book += book.book_id + ",";
            new_book += book.book_name  + ",";
            new_book += book.release_year + ",";

            authors_csv += new_author + new_book + '\n';
        })

    })
    download(authors_csv, "data.csv", "text/plain");
}

function download(content, fileName, contentType) {
    const a = document.createElement("a");
    const file = new Blob([content], { type: contentType });
    a.href = URL.createObjectURL(file);
    a.download = fileName;
    a.click();
}

function convert_date(date) {
    if(date === null) {
        return "Still alive";
    }
    let months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    let day = date.substring(8, 10);
    let month = parseInt(date.substring(5, 7))
    let year = date.substring(0, 4);
    return String(day + " " + months[month - 1] + " " + year);
}


function fetch_json() {
    let url = 'http://localhost:8090/authors'

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error has occurred while fetching data.');
        }
        return response.json();
    })
    .then(authors => {
        authorData.setAuthors(authors.response)
        download_json()
    })
    .catch((error) => {
        document.getElementById('error_field').innerHTML = error;
        authorData.setAuthors([])
    });
}

function fetch_csv() {
    let url = 'http://localhost:8090/authors'

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error has occurred while fetching data.');
        }
        return response.json();
    })
    .then(authors => {
        authorData.setAuthors(authors.response)
        download_csv()
    })
    .catch((error) => {
        document.getElementById('error_field').innerHTML = error;
        authorData.setAuthors([])
    });
}

function showDatatable() {
    let url = 'http://localhost:8090/datatable';
    window.location.href = url;
}

function showUserProfile() {
    let url = 'http://localhost:8090/user';
    window.location.href = url;
}

function showIndex() {
    let url = 'http://localhost:8090/';
    window.location.href = url;
}