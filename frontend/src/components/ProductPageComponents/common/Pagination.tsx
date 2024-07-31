import useProducts from "../../../hooks/ProductsPageHooks/useProducts";

interface PaginationProps {
    postsPerPage: number
    totalPosts: number
}

function Pagination({ postsPerPage, totalPosts }: PaginationProps) {

    const pageNumbers = [];
    for(let i = 1; i <= Math.ceil(totalPosts / postsPerPage); i++)
        pageNumbers.push(i);

    return (
        <nav className="ms-5 mt-5">
            <ul className="pagination">
                {
                    pageNumbers.map(number =>
                        <li key={number} className="page-item">
                            <a href="#" className="page-link">
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