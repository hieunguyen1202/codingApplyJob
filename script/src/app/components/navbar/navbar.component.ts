import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MegaMenuItem } from 'primeng/api';
import { StorageService } from '../../services/storage/storage.service';
import { Store } from '@ngrx/store';
import { ProfileService } from '../../services/profile/profile.service';
import { storeUser } from '../../shared/login.action';
import { getUser } from '../../shared/login.selector';
import { NzMessageModule, NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  items: MegaMenuItem[] | undefined;
  count: string = '5';
  isLogin: boolean = StorageService.isLoggedIn();
  email: string = StorageService.getEmail();
  profile: any;
  user: any;

  constructor(
    private router: Router,
    private profileService: ProfileService,
    private store: Store<{ user: any }>,
    private msg : NzMessageService
  ) {}

  ngOnInit() {
    this.store.select(getUser).subscribe((res) => {
      if (res) {
        this.user = res; // Update user directly from the store
        this.updateMenuItems(); // Update menu items when user data changes
      }
    });

    if (this.isLogin && this.email) {
      this.profileService.findAccountByEmail({ email: this.email }).subscribe(
        (res) => {
          this.profileService
            .viewProfileByEmail({ id: res.metadata.id })
            .subscribe(
              (res) => {
                this.store.dispatch(storeUser({ user: res.metadata }));
                this.profile = res.metadata;
                this.updateMenuItems(); // Update menu items when profile data changes
              },
              (error) => {
                this.msg.error('error occured')
              }
            );
        },
        (error) => {
           this.msg.error('error occured')
        }
      );
    }
  }

  updateMenuItems() {
    this.items = [
      {
        label:
          this.user && this.user.avatar
            ? `<img src="data:image/png;base64,${this.user.avatar}" class="w-10 h-10 rounded-full" alt="">`
            : this.profile && this.profile.avatar
            ? `<img src="data:image/png;base64,${this.profile.avatar}" class="w-10 h-10 rounded-full" alt="">`
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
  }

  logout() {
    StorageService.signout();
    this.router.navigateByUrl('/login');
  }
}
