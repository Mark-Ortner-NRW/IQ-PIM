import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { PageThreeComponent } from './page-three.component';

export const PAGE_THREE_ROUTE: Route = {
  path: 'page-three',
  component: PageThreeComponent,
  data: {
    authorities: [],
    pageTitle: 'page-one.title',
  },
  canActivate: [UserRouteAccessService],
};
