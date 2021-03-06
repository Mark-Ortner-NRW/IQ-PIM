import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ControlDemoComponent } from './control-demo.component';
import { PAGE_ONE_ROUTE } from './page-one/page-one.route';
import { PAGE_TWO_ROUTE } from './page-two/page-two.route';
import { PAGE_THREE_ROUTE } from './page-three/page-three.route';
import { PAGE_FOUR_ROUTE } from 'app/control-demo/page-four/page-four.route';

export const CONTROL_DEMO_ROUTE: Route = {
  path: 'control-demo',
  component: ControlDemoComponent,
  data: {
    authorities: [],
    pageTitle: 'control-demo.title',
  },
  canActivate: [UserRouteAccessService],
  children: [PAGE_ONE_ROUTE, PAGE_TWO_ROUTE, PAGE_THREE_ROUTE, PAGE_FOUR_ROUTE],
};
