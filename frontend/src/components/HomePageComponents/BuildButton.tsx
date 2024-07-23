import machineIcon from '../../assets/svgs/HomePage/machine-icon.svg';

function BuildButton() {
    return (
        <div className="d-flex container-fluid btn-container justify-content-center">
            <a href="/setup"><button type="button" className="HomePage_build-button btn btn-primary btn-lg">Start Building <img src={machineIcon} className="HomePage_machine-icon img-fluid mx-2"/></button></a>
        </div>
    );
}

export default BuildButton;