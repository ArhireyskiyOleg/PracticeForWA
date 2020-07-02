import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable, Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AlertService {
    private routeChange = false;
    private subj = new Subject<any>();

    constructor(private router: Router) {

        this.router.events.subscribe(event => {
            if (event instanceof NavigationStart) {
                if (this.routeChange) {

                    this.routeChange = false;
                } else {

                    this.clear();
                }
            }
        });
    }

    getAlert(): Observable<any> {
        return this.subj.asObservable();
    }

    success(message: string, routeChange = false) {
        this.routeChange = routeChange;
        this.subj.next({ type: 'Successfull', text: message });
    }

    error(message: string, routeChange = false) {
        this.routeChange = routeChange;
        this.subj.next({ type: 'Error', text: message });
    }

    clear() {
        this.subj.next();
    }
}
