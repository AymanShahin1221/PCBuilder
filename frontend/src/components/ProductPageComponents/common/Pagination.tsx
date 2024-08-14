interface PaginationProps {
    entriesPerPage: number
    totalEntries: number
    paginate: (number: number) => void
    currentPage: number
}

function Pagination({ entriesPerPage, totalEntries, paginate, currentPage }: PaginationProps) {

    const pageNumbers = [];
    for(let i = 0; i <= Math.ceil(totalEntries / entriesPerPage); i++)
        pageNumbers.push(i);

    return (
        <nav className="ms-5 mt-5">
            <ul className="pagination">
                {
                    pageNumbers.map(number =>
                            <li key={number} className={currentPage === number ? "page-item active" : "page-item"}>
                                <a href={"#page=" + number} onClick={() => paginate(number)} className="page-link">
                                    {number + 1}
                                </a>
                            </li>
                    )
                }
            </ul>
        </nav>
    );
}

export default Pagination;