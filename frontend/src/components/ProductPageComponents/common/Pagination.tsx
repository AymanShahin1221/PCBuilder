interface PaginationProps {
    entriesPerPage: number
    totalEntries: number
    paginate: (number: number) => void;
}

function Pagination({ entriesPerPage, totalEntries, paginate }: PaginationProps) {

    const pageNumbers = [];
    for(let i = 1; i <= Math.ceil(totalEntries / entriesPerPage); i++)
        pageNumbers.push(i);

    return (
        <nav className="ms-5 mt-5">
            <ul className="pagination">
                {
                    pageNumbers.map(number =>
                        <li key={number} className="page-item">
                            <a href="#" onClick={() => paginate(number)} className="page-link">
                                {number}
                            </a>
                        </li>
                    )
                }
            </ul>
        </nav>
    );
}

export default Pagination;