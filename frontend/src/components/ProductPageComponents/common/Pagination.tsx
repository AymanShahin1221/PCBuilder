interface PaginationProps {
    entriesPerPage: number
    totalEntries: number
    paginate: (number: number) => void
    currentPage: number
    maxPageNumbersToShow?: number
}

function Pagination({ entriesPerPage, totalEntries, paginate, currentPage, maxPageNumbersToShow = 20 }: PaginationProps) {
    const pageNumbers = [];
    const totalPages = Math.ceil(totalEntries / entriesPerPage);

    for (let i = 0; i <= totalPages - 1; i++)
        pageNumbers.push(i);

    const startPage = Math.max(currentPage - Math.floor(maxPageNumbersToShow / 2), 1);
    const endPage = Math.min(startPage + maxPageNumbersToShow - 1, totalPages);

    const visiblePageNumbers = pageNumbers.slice(startPage - 1, endPage);

    return (
        <nav className="ms-5 mt-5">
            <ul className="pagination">
                <li className={currentPage === 1 ? "page-item disabled" : "page-item"}>
                    <a href={"#page=" + (currentPage + 1)} onClick={() => currentPage > 1 && paginate(currentPage - 1)} className="page-link">
                        <b>&lt;&lt;</b>
                    </a>
                </li>
                {
                    visiblePageNumbers.map(number =>
                        <li key={number} className={currentPage === number ? "page-item active" : "page-item"}>
                            <a href={"#page=" + (number + 1)} onClick={() => paginate(number)} className="page-link">
                                {number + 1}
                            </a>
                        </li>
                    )
                }
                <li className={currentPage === totalPages ? "page-item disabled" : "page-item"}>
                    <a href={"#page=" + (currentPage + 1)} onClick={() => currentPage < totalPages && paginate(currentPage + 1)} className="page-link">
                        <b>&gt;&gt;</b>
                    </a>
                </li>
            </ul>
        </nav>
    );
}

export default Pagination;
