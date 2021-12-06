
const msgModal = document.getElementById("msgModal");
const msgTitle = document.getElementById("msgTitle");
const msgBody = document.getElementById("msgBody");
const msgTextarea = document.getElementById("msgTextarea");
const globalWaiting = document.getElementById("globalWaiting");

const confModal = document.getElementById("confModal");
const confTitle = document.getElementById("confTitle");
const confBody = document.getElementById("confBody");
const confCancelBTN = document.getElementById("confCancelBTN");
const confBTN = document.getElementById("confBTN");
const confSpinner = document.getElementById("confSpinner");

function showMSG(titulo, cuerpo, log = null, isHtml = false) {
    msgTitle.innerText = titulo;
    if (isHtml)
        msgBody.innerHTML = cuerpo;
    else
        msgBody.innerText = cuerpo;
    if (log === null) {
        msgTextarea.classList.add("visually-hidden");
    } else {
        console.log(log);
        msgTextarea.value = log;
        msgTextarea.classList.remove("visually-hidden");
    }
    hideAllModals(null, msgModal);
}

function showConf(titulo, cuerpo, requiresLoading = false) {
    confTitle.innerText = titulo;
    confBody.innerText = cuerpo;
    confBTN.disabled = false;
    confSpinner.classList.add("visually-hidden");
    hideAllModals(null, confModal);
    return new Promise((resolve, reject) => {
        confBTN.onclick = () => {
            if (requiresLoading) {
                confBTN.disabled = true;
                confSpinner.classList.remove("visually-hidden");
            } else {
                hideAllModals();
            }
            resolve();
        };
        confCancelBTN.onclick = () => {
            hideAllModals();
            reject();
        };
    });
}
function hideAllModals(except = null, open = null) {
    if (except !== null && except instanceof Element)
        except = except.id;
    if (open !== null && !(open instanceof Element))
        open = document.getElementById(open);

    let timeout;
    if (document.querySelector(".modal.show") === null) {
        timeout = 1;
    } else {
        timeout = 500;
        globalLoaderShow();
    }
    setTimeout(() => {
        globalLoaderHide();
        let modals = document.getElementsByClassName("modal");
        for (let i = 0; i < modals.length; i++) {
            if (modals[i].id != except)
                bootstrap.Modal.getOrCreateInstance(modals[i]).hide();
        }
        if (open != null) {
            let modal = bootstrap.Modal.getOrCreateInstance(open);
            modal.show();
            let videos = document.getElementsByTagName("video");
            for (let i = 0; i < videos.length; i++) {
                videos[i].pause()
            }
            open.addEventListener('hidden.bs.modal', () => {
                let videos = open.getElementsByTagName("video");
                for (let i = 0; i < videos.length; i++) {
                    videos[i].pause()
                }
            });
        }
    }, timeout);
}

function globalLoaderShow(text = null) {
    if (text === null) {
        globalWaiting.firstElementChild.classList.add("visually-hidden");
    } else {
        globalWaiting.firstElementChild.classList.remove("visually-hidden");
        globalWaiting.firstElementChild.innerText = text;
    }
    globalWaiting.classList.remove("visually-hidden");
}

function globalLoaderHide() {
    globalWaiting.classList.add("visually-hidden");
}