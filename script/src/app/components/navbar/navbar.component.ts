import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MegaMenuItem } from 'primeng/api';
import { StorageService } from '../../services/storage/storage.service';
import { Store } from '@ngrx/store';
import { ProfileService } from '../../services/profile/profile.service';
import { storeUser } from '../../shared/login.action';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent {
  items: MegaMenuItem[] | undefined;
  count: string = '5';
  isLogin: boolean = StorageService.isLoggedIn();
  email: string = StorageService.getEmail();
  profile: any;

  constructor(
    private router: Router,
    private profileService: ProfileService,
    private store: Store<{ user: any }>
  ) {}

  ngOnInit() {
    if (this.isLogin) {
      if (StorageService.getEmail() !== '') {
        console.log('====================================');
        console.log(StorageService.getEmail());
        console.log('====================================');
      }

      if (this.email) {
        this.profileService.findAccountByEmail({ email: this.email }).subscribe(
          (res) => {
            this.profileService
              .viewProfileByEmail({ id: res.metadata.id })
              .subscribe(
                (res) => {
                  this.store.dispatch(storeUser({ user: res.metadata }));
                  this.profile = res.metadata;
                  this.items = [
                    {
                      label: this.profile.avatar
                        ? `<img src="${this.profile.avatar}" class="w-10 h-10 rounded-full" alt="">`
                        : `<img src="assets/images/avatar.png" class="w-10 h-10 rounded-full" alt="">`,
                      icon: '',
                      items: [
                        [
                          {
                            items: [
                              {
                                label: 'Hồ sơ của tôi',
                                routerLink: '/profile',
                              },
                              {
                                label: 'Đăng xuất',
                                command: (event) => this.logout(),
                              },
                            ],
                          },
                        ],
                      ],
                    },
                  ];
                },
                (error) => {
                  // Handle error when fetching profile information
                }
              );
          },
          (error) => {
            // Handle error when fetching account information
          }
        );
      }
    }
  }

  logout() {
    StorageService.signout();
    this.router.navigateByUrl('/login');
  }
}
