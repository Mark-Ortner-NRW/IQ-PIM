import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ControlDemoComponent } from './control-demo.component';
import { PAGE_ONE_ROUTE } from './page-one/page-one.route';
import { PAGE_TWO_ROUTE } from './page-two/page-two.route';

export const CONTROL_DEMO_ROUTE: Route = {
  path: 'control-demo',
  component: ControlDemoComponent,
  data: {
    authorities: [],
    pageTitle: 'control-demo.title',
  },
  canActivate: [UserRouteAccessService],
  children: [PAGE_ONE_ROUTE, PAGE_TWO_ROUTE],
};
