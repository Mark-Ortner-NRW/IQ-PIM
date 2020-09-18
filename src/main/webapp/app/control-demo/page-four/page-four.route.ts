import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { PageFourComponent } from './page-four.component';

export const PAGE_FOUR_ROUTE: Route = {
  path: 'page-four',
  component: PageFourComponent,
  data: {
    authorities: [],
    pageTitle: 'page-one.title',
  },
  canActivate: [UserRouteAccessService],
};
