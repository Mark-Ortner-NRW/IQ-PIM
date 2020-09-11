import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['landing-page.component.scss'],
})
export class LandingPageComponent implements OnInit {
  message: string;

  constructor() {
    this.message = 'LandingPageComponent message';
  }

  ngOnInit(): void {}
}
